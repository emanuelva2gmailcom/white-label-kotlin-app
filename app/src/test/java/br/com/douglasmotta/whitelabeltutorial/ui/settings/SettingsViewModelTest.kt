package br.com.douglasmotta.whitelabeltutorial.ui.settings

import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthRepository
import br.com.douglasmotta.whitelabeltutorial.api.auth.FailureFakeAuthService
import br.com.douglasmotta.whitelabeltutorial.api.auth.SuccessFakeAuthService
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignOutUseCaseImpl
import br.com.douglasmotta.whitelabeltutorial.util.CoroutineTestCase
import br.com.douglasmotta.whitelabeltutorial.util.Result
import br.com.douglasmotta.whitelabeltutorial.util.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SettingsViewModelTest: CoroutineTestCase() {

    lateinit var viewModelFailureTest: SettingsViewModel

    lateinit var viewModelSuccessTest: SettingsViewModel

    @Before
    fun setupSuccessViewModel() {
        val useCase = SignOutUseCaseImpl(
            AuthRepository(
                SuccessFakeAuthService()
            )
        )

        viewModelSuccessTest = SettingsViewModel(
            useCase
        )
    }

    @Before
    fun setupFailureViewModel() {
        val useCase = SignOutUseCaseImpl(
            AuthRepository(
                FailureFakeAuthService()
            )
        )

        viewModelFailureTest = SettingsViewModel(
            useCase
        )
    }

    @Test
    fun `when function logout get success then sets resultLiveData`() {
        // when
        viewModelSuccessTest.logout()

        // then
        Assert.assertTrue(
            viewModelSuccessTest.resultData.getOrAwaitValue() is Result.Success
        )
    }

    @Test
    fun `when function logout get failure then sets resultLiveData`() {
        // when
        viewModelFailureTest.logout()

        // then
        Assert.assertTrue(
            viewModelFailureTest.resultData.getOrAwaitValue() is Result.Error
        )
    }
}