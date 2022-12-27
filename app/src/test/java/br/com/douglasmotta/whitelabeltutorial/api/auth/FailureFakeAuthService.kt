package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.UserAuth
import java.lang.Exception

class FailureFakeAuthService: AuthService {
    override suspend fun singIn(form: SignInForm): Result<LoggedInUser>
            = Result.Error(Exception("Error"))

    override suspend fun signUp(form: SignUpForm) = throw Exception("Error")

    override fun getUser(): UserAuth = throw Exception("Error")

    override fun signOut() = throw Exception("Error")
}