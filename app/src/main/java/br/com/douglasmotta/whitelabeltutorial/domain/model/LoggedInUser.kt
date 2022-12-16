package br.com.douglasmotta.whitelabeltutorial.domain.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val email: String,
    val password: String
)