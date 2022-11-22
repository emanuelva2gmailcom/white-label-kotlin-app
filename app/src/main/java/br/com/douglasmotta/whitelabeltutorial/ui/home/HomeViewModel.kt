package br.com.douglasmotta.whitelabeltutorial.ui.home

import androidx.lifecycle.ViewModel
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.GetUserAuthUseCase
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserAuthUseCase: GetUserAuthUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    fun getUserEmail(): String {
        return getUserAuthUseCase().email!!
    }

    fun logout() {
        signOutUseCase()
    }
}