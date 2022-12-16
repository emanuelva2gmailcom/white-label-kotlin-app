package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm
import com.google.firebase.auth.FirebaseUser

interface AuthService {

    suspend fun singIn(form: SignInForm): Result<LoggedInUser>

    suspend fun signUp(form: SignUpForm)

    fun getUser(): FirebaseUser

    fun signOut()
}