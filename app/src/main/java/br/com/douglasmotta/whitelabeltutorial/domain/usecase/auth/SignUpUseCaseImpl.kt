package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignUpUseCase {

    override fun invoke(email: String, password: String) {
        authRepository.createUserWithEmailAndPassword(SignInForm(email, password))
    }
}