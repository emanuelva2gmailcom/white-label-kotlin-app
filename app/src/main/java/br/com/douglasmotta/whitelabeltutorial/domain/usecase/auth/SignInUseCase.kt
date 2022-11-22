package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface SignInUseCase {

    operator fun invoke(email: String, password: String): Task<AuthResult>
}