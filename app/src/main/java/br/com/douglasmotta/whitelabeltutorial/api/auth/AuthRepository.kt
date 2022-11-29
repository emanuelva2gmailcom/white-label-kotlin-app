package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) {
    fun signIn(email: String, password: String): Task<AuthResult> =
        authService.singIn(email, password)

    fun createUserWithEmailAndPassword(user: SignInForm) =
        authService.createUserWithEmailAndPassword(user)

    fun getUser(): FirebaseUser = authService.getUser()

    fun signOut() = authService.signOut()
}