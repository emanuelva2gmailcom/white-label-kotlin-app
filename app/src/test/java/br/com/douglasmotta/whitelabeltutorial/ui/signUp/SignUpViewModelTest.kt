package br.com.douglasmotta.whitelabeltutorial.ui.signUp

import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthRepository
import br.com.douglasmotta.whitelabeltutorial.api.auth.FailureFakeAuthService
import br.com.douglasmotta.whitelabeltutorial.api.auth.SuccessFakeAuthService
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignUpUseCaseImpl
import br.com.douglasmotta.whitelabeltutorial.ui.signUp.models.SignUpFormState
import br.com.douglasmotta.whitelabeltutorial.ui.signUp.models.SignUpResult
import br.com.douglasmotta.whitelabeltutorial.util.CoroutineTestCase
import br.com.douglasmotta.whitelabeltutorial.util.dataTest.*
import br.com.douglasmotta.whitelabeltutorial.util.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SignUpViewModelTest: CoroutineTestCase() {

    lateinit var viewModelFailureTest: SignUpViewModel

    lateinit var viewModelSuccessTest: SignUpViewModel

    @Before
    fun setupSuccessViewModel() {
        val useCase = SignUpUseCaseImpl(
            AuthRepository(
                SuccessFakeAuthService()
            )
        )

        viewModelSuccessTest = SignUpViewModel(
            useCase
        )
    }

    @Before
    fun setupFailureViewModel() {
        val useCase = SignUpUseCaseImpl(
            AuthRepository(
                FailureFakeAuthService()
            )
        )

        viewModelFailureTest = SignUpViewModel(
            useCase
        )
    }

    @Test
    fun `when function signUp get success then sets signUpResultLiveData`() = runBlocking {
        // when
        viewModelSuccessTest.signUp(
            SignUpForm(
                USER_AUTH_NAME_TEST,
                USER_AUTH_USERNAME_TEST,
                USER_AUTH_EMAIL_TEST,
                USER_AUTH_PASSWORD_TEST
            )
        )

        // then
        Assert.assertEquals(
            viewModelSuccessTest.signUpResult.getOrAwaitValue(),
            SignUpResult(
                success = true
            )
        )
    }

    @Test
    fun `when function signUp get failure then sets signUpResultLiveData`() = runBlocking {
        // when
        viewModelFailureTest.signUp(
            SignUpForm(
                USER_AUTH_NAME_TEST,
                USER_AUTH_USERNAME_TEST,
                USER_AUTH_EMAIL_TEST,
                USER_AUTH_PASSWORD_TEST
            )
        )

        // then
        Assert.assertEquals(
            viewModelFailureTest.signUpResult.getOrAwaitValue(),
            SignUpResult(error = R.string.signUp_failed)
        )
    }

    @Test
    fun `when function signUpDataChanged with form valid then sets signUpFormStateLiveData`()
            = runBlocking {
        // when
        viewModelSuccessTest.signUpDataChanged(
            SignUpForm(
                USER_AUTH_NAME_TEST,
                USER_AUTH_USERNAME_TEST,
                USER_AUTH_EMAIL_TEST,
                USER_AUTH_PASSWORD_TEST
            )
        )

        // then
        Assert.assertEquals(
            viewModelSuccessTest.signUpFormState.getOrAwaitValue(),
            SignUpFormState(isDataValid = true)
        )
    }

    @Test
    fun `when function signUpDataChanged with email invalid then sets signUpFormStateLiveData`()
    = runBlocking {
        // when
        viewModelSuccessTest.signUpDataChanged(
            SignUpForm(
                USER_AUTH_NAME_TEST,
                USER_AUTH_USERNAME_TEST,
                USER_AUTH_INVALID_EMAIL_TEST,
                USER_AUTH_PASSWORD_TEST
            )
        )

        // then
        Assert.assertEquals(
            viewModelSuccessTest.signUpFormState.getOrAwaitValue(),
            SignUpFormState(emailError = R.string.invalid_email)
        )
    }

    @Test
    fun `when function signUpDataChanged with password invalid then sets signUpFormStateLiveData`()
            = runBlocking {
        // when
        viewModelSuccessTest.signUpDataChanged(
            SignUpForm(
                USER_AUTH_NAME_TEST,
                USER_AUTH_USERNAME_TEST,
                USER_AUTH_EMAIL_TEST,
                USER_AUTH_INVALID_PASSWORD_TEST
            )
        )

        // then
        Assert.assertEquals(
            viewModelSuccessTest.signUpFormState.getOrAwaitValue(),
            SignUpFormState(passwordError = R.string.invalid_password)
        )
    }
}