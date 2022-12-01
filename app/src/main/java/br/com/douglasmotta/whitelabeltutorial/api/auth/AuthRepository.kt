package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
class AuthRepository @Inject constructor(
    private val authService: AuthService
) {

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    suspend fun signIn(email: String, password: String): Result<LoggedInUser> {
        val result = authService.singIn(email, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    fun createUserWithEmailAndPassword(user: SignInForm) =
        authService.createUserWithEmailAndPassword(user)

    fun getUser(): FirebaseUser = authService.getUser()

    fun signOut() {
        user = null
        authService.signOut()
    }
}

