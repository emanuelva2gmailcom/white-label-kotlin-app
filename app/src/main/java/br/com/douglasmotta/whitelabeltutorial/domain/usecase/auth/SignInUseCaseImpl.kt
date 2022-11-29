package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignInUseCase {

    override fun invoke(email: String, password: String): Task<AuthResult> {
        return authRepository.signIn(email, password)
    }
}