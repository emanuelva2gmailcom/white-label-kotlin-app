package br.com.douglasmotta.whitelabeltutorial.ui.settings

import androidx.lifecycle.ViewModel
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignOutUseCase
import br.com.douglasmotta.whitelabeltutorial.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    fun logout() {
        signOutUseCase()
    }
}