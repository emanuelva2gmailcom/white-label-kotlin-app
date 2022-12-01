package br.com.douglasmotta.whitelabeltutorial.ui

import androidx.lifecycle.ViewModel
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignOutUseCase
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject lateinit var signOutUseCase: SignOutUseCase
    /* TODO: Faire le Feature de redirection pour le LoginActivity si l'utilisateur n'est pas connecté.  */
    fun logout() {
        signOutUseCase()
    }
}