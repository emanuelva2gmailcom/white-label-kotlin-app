package br.com.douglasmotta.whitelabeltutorial.ui.home

import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.GetUserAuthUseCase
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignOutUseCase
import br.com.douglasmotta.whitelabeltutorial.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserAuthUseCase: GetUserAuthUseCase
) : BaseViewModel() {

    fun getUserEmail(): String {
        return getUserAuthUseCase().email!!
    }
}