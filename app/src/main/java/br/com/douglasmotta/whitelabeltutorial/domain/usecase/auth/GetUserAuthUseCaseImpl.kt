package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.data.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetUserAuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetUserAuthUseCase {

    override fun invoke(): FirebaseUser {
        return authRepository.getUser()
    }
}