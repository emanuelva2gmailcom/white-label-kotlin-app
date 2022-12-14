package br.com.douglasmotta.whitelabeltutorial.ui.login.models

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)