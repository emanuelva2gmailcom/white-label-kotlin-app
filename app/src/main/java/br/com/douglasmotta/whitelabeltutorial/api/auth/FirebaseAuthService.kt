package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.api.datasource.user.UserRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userRepository: UserRepository
) : AuthService {

    override fun singIn(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
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