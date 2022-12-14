package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthRepository
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignOutUseCase {

    override fun invoke() {
        authRepository.signOut()
    }
}