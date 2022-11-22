package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import com.google.firebase.auth.FirebaseUser

interface GetUserAuthUseCase {

    operator fun invoke(): FirebaseUser
}