package br.com.douglasmotta.whitelabeltutorial.ui.signUp.models

data class SignUpFormState(
    val nameError: Int? = null,
    val usernameError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
