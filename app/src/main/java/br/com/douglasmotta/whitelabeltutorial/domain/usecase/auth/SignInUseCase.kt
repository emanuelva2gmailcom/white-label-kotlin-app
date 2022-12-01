package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.api.auth.Result
import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface SignInUseCase {

    suspend operator fun invoke(email: String, password: String): Result<LoggedInUser>
}