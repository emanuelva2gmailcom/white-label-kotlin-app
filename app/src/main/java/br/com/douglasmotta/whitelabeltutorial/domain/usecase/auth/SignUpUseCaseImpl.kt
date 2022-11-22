package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.data.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignUpUseCase {

    override fun invoke(email: String, password: String): Task<AuthResult> {
        return authRepository.createUserWithEmailAndPassword(email, password)
    }
}