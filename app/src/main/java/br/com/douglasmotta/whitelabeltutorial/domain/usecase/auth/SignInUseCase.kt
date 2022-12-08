package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.api.auth.Result
import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface SignInUseCase {

    suspend operator fun invoke(signInForm: SignInForm): Result<LoggedInUser>
}