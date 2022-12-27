package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.UserAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetUserAuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetUserAuthUseCase {

    override fun invoke(): UserAuth {
        return authRepository.getUser()
    }
}