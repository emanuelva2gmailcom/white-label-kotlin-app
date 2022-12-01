package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthService {

    suspend fun singIn(email: String, password: String): Result<LoggedInUser>

    fun createUserWithEmailAndPassword(user: SignInForm)

    fun getUser(): FirebaseUser

    fun signOut()
}