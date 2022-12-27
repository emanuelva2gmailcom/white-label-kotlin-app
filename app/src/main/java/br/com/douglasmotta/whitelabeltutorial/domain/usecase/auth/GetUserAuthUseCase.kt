package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.UserAuth
import com.google.firebase.auth.FirebaseUser

interface GetUserAuthUseCase {

    operator fun invoke(): UserAuth
}