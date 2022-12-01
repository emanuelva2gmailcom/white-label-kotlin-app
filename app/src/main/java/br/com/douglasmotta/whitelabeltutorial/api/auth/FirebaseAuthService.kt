package br.com.douglasmotta.whitelabeltutorial.api.auth

import android.util.Log
import br.com.douglasmotta.whitelabeltutorial.api.datasource.user.UserRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userRepository: UserRepository
) : AuthService {

    override suspend fun singIn(email: String, password: String): Result<LoggedInUser> {
        return suspendCoroutine { continuation ->
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
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

    override fun createUserWithEmailAndPassword(user: SignInForm) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                it.user?.let {
                    val user = User(
                        uid = it.uid,
                        email = it.email!!,
                        admin = true,
                    )
                    userRepository.createUser(user)
                }
            }
    }

    override fun getUser(): FirebaseUser {
        return firebaseAuth.currentUser!!
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}