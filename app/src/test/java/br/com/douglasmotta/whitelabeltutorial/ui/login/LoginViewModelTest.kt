package br.com.douglasmotta.whitelabeltutorial.ui.login

import android.util.Patterns
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthRepository
import br.com.douglasmotta.whitelabeltutorial.api.auth.FailureFakeAuthService
import br.com.douglasmotta.whitelabeltutorial.api.auth.SuccessFakeAuthService
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignInUseCaseImpl
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoginFormState
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoginResult
import br.com.douglasmotta.whitelabeltutorial.util.ConfigTest
import br.com.douglasmotta.whitelabeltutorial.util.CoroutineTestCase
import br.com.douglasmotta.whitelabeltutorial.util.dataTest.*
import br.com.douglasmotta.whitelabeltutorial.util.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginViewModelTest: CoroutineTestCase() {

    lateinit var viewModelFailureTest: LoginViewModel

    lateinit var viewModelSuccessTest: LoginViewModel

    @Before
    fun setupSuccessViewModel() {
        val useCase = SignInUseCaseImpl(
            AuthRepository(
                SuccessFakeAuthService()
            )
        )

        viewModelSuccessTest = LoginViewModel(
            useCase,
            ConfigTest()
        )
    }

    @Before
    fun setupFailureViewModel() {
        val useCase = SignInUseCaseImpl(
            AuthRepository(
                FailureFakeAuthService()
            )
        )

        viewModelFailureTest = LoginViewModel(
            useCase,
            ConfigTest()
        )
    }

    @Test
    fun `when function login get success then sets loginResultLiveData`() = runBlocking {
        // when
        viewModelSuccessTest.login(
            USER_AUTH_EMAIL_TEST,
            USER_AUTH_PASSWORD_TEST
        )

        // then
        Assert.assertEquals(viewModelSuccessTest.loginResult.getOrAwaitValue(), LoginResult(
            success = LOGGED_IN_USER_VIEW_TEST
        ))
    }

    @Test
    fun `when function login get failure then sets loginResultLiveData`()  = runBlocking {
        // when
        viewModelFailureTest.login(
            USER_AUTH_EMAIL_TEST,
            USER_AUTH_PASSWORD_TEST
        )

        // then
        Assert.assertEquals(
            viewModelFailureTest.loginResult.getOrAwaitValue(),
            LoginResult(error = R.string.login_failed)
        )
    }

    @Test
    fun `when function loginDataChanged with email invalid then sets loginFormLiveData`() = runBlocking {
        // when
        viewModelSuccessTest.loginDataChanged(
            USER_AUTH_INVALID_EMAIL_TEST,
            USER_AUTH_PASSWORD_TEST
        )

        // then
        Assert.assertEquals(
            viewModelSuccessTest.loginFormState.getOrAwaitValue(),
            LoginFormState(usernameError = R.string.invalid_username)
        )
    }

    @Test
    fun `when function loginDataChanged with password invalid then sets loginFormLiveData`() = runBlocking {
        // when
        viewModelSuccessTest.loginDataChanged(
            USER_AUTH_EMAIL_TEST,
            USER_AUTH_INVALID_PASSWORD_TEST
        )

        // then
        Assert.assertEquals(
            viewModelSuccessTest.loginFormState.getOrAwaitValue(),
            LoginFormState(passwordError = R.string.invalid_password)
        )
    }
}