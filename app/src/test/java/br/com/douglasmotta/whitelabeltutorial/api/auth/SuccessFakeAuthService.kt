package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.UserAuth
import br.com.douglasmotta.whitelabeltutorial.util.dataTest.LOGGED_IN_USER_TEST
import br.com.douglasmotta.whitelabeltutorial.util.dataTest.USER_AUTH_TEST

class SuccessFakeAuthService: AuthService {
    override suspend fun singIn(form: SignInForm): Result<LoggedInUser>
        = Result.Success(LOGGED_IN_USER_TEST)

    override suspend fun signUp(form: SignUpForm) = Unit

    override fun getUser(): UserAuth = USER_AUTH_TEST

    override fun signOut() = Unit
}