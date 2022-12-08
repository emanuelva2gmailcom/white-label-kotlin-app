package br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm

interface SignUpUseCase {

    suspend operator fun invoke(form: SignUpForm)
}