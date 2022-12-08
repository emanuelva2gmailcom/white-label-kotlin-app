package br.com.douglasmotta.whitelabeltutorial.api.auth

import android.util.Log
import br.com.douglasmotta.whitelabeltutorial.api.datasource.user.UserRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userRepository: UserRepository
) : AuthService {

    override suspend fun singIn(form: SignInForm): Result<LoggedInUser> {
        return suspendCoroutine { continuation ->
            firebaseAuth
                .signInWithEmailAndPassword(form.email, form.password)
                .addOnCompleteListener {
                    Log.d("firebase auth", "complete")
                    if (it.isSuccessful) {
                        Log.d("firebase auth", "success")
                        it.result!!.user?.let {
                            continuation.resumeWith(
                                kotlin.Result.success(
                                    Result.Success(LoggedInUser(it.uid, it.email!!))
                                )
                            )
                        }
                    } else {
                        Log.d("firebase auth", "failure")
                        continuation.resumeWith(
                            kotlin.Result.success(
                                Result.Error(IOException("Error logging in", it.exception!!))
                            )
                        )
                    }
                }
        }
    }

    private suspend fun createUserWithEmailAndPassword(user: SignUpForm): User {
        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        it.result!!.user?.let {

                            val userReturn = User(
                                uid = it.uid,
                                email = it.email!!,
                                name = user.name,
                                username = user.username,
                                created = Calendar.getInstance().timeInMillis,
                                updated = Calendar.getInstance().timeInMillis
                            )

                            continuation.resumeWith(
                                kotlin.Result.success(
                                    userReturn
                                )
                            )
                        }
                    } else {
                        continuation.resumeWith(kotlin.Result.failure(it.exception!!))
                    }
                }
        }
    }

    override suspend fun signUp(form: SignUpForm) {
        try {
            val userCreated = createUserWithEmailAndPassword(form)

            userRepository.createUser(userCreated)
        } catch (e: Exception) { throw e }
    }

    override fun getUser(): FirebaseUser {
        return firebaseAuth.currentUser!!
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}