package br.com.douglasmotta.whitelabeltutorial.api.auth

import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.UserAuth
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

    suspend fun signIn(form: SignInForm): Result<LoggedInUser> {
        val result = authService.singIn(form)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    suspend fun signUp(form: SignUpForm) =
        authService.signUp(form)

    fun getUser(): UserAuth = authService.getUser()

    fun signOut() {
        user = null
        authService.signOut()
    }
}

