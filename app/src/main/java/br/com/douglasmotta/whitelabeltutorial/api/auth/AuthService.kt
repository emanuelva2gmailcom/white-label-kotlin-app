package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthService {

    fun singIn(email: String, password: String): Task<AuthResult>

    fun createUserWithEmailAndPassword(user: SignInForm)

    fun getUser(): FirebaseUser

    fun signOut()
}