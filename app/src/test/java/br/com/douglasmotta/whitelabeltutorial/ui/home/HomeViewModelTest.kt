package br.com.douglasmotta.whitelabeltutorial.ui.home

import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthRepository
import br.com.douglasmotta.whitelabeltutorial.api.auth.FailureFakeAuthService
import br.com.douglasmotta.whitelabeltutorial.api.auth.SuccessFakeAuthService
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.GetUserAuthUseCaseImpl
import br.com.douglasmotta.whitelabeltutorial.util.CoroutineTestCase
import br.com.douglasmotta.whitelabeltutorial.util.dataTest.USER_AUTH_EMAIL_TEST
import br.com.douglasmotta.whitelabeltutorial.util.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HomeViewModelTest: CoroutineTestCase() {

    lateinit var viewModelFailureTest: HomeViewModel

    lateinit var viewModelSuccessTest: HomeViewModel

    @Before
    fun setupSuccessViewModel() {
        val useCase = GetUserAuthUseCaseImpl(
            AuthRepository(
                SuccessFakeAuthService()
            )
        )

        viewModelSuccessTest = HomeViewModel(
            useCase
        )
    }

    @Before
    fun setupFailureViewModel() {
        val useCase = GetUserAuthUseCaseImpl(
            AuthRepository(
                FailureFakeAuthService()
            )
        )

        viewModelFailureTest = HomeViewModel(
            useCase
        )
    }

    @Test
    fun `when function getUserEmail gets success then sets userInformationLiveData`() = runBlocking {
        // when
        viewModelSuccessTest.getUserEmail()

        // then
        Assert.assertEquals(
            viewModelSuccessTest.userInformationData.getOrAwaitValue(),
            USER_AUTH_EMAIL_TEST
        )
    }

    @Test
    fun `when function getUserEmail gets failure then sets errorMessageLiveData`() = runBlocking {
        // when
        viewModelFailureTest.getUserEmail()

        // then
        Assert.assertEquals(
            viewModelFailureTest.errorMessageData.getOrAwaitValue(),
            R.string.error_message_default
        )
    }
}