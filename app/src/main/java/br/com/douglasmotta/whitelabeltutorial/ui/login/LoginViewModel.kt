package br.com.douglasmotta.whitelabeltutorial.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import br.com.douglasmotta.whitelabeltutorial.api.auth.Result

import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.config.Config
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignInUseCase
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoggedInUserView
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoginFormState
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    config: Config
) : ViewModel() {

    private val _authVariableVisibility = MutableLiveData(config.signUpLink)
    val authVariableVisibility: LiveData<Int> = _authVariableVisibility

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(email: String, password: String) = viewModelScope.launch {

        val result = signInUseCase(SignInForm(email, password))

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.email))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(email: String, password: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isEmailValid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length < 10
    }
}