package br.com.douglasmotta.whitelabeltutorial.ui.signUp

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignInForm
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignUpUseCase
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoginFormState
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoginResult
import br.com.douglasmotta.whitelabeltutorial.ui.signUp.models.SignUpFormState
import br.com.douglasmotta.whitelabeltutorial.ui.signUp.models.SignUpResult
import br.com.douglasmotta.whitelabeltutorial.util.EMAIL_ADDRESS_REGEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpForm = MutableLiveData<SignUpFormState>()
    val signUpFormState: LiveData<SignUpFormState> = _signUpForm

    private val _signUpResult = MutableLiveData<SignUpResult>()
    val signUpResult: LiveData<SignUpResult> = _signUpResult

    fun signUp(form: SignUpForm) = viewModelScope.launch{
        try {
            signUpUseCase(form)
            _signUpResult.value = SignUpResult(success = true)
        } catch (e: Exception) {
            _signUpResult.value = SignUpResult(error = R.string.signUp_failed)
        }
    }

    fun signUpDataChanged(form: SignUpForm) {
        when {
            !isNameValid(form.name) ->
                _signUpForm.value = SignUpFormState(nameError = R.string.invalid_name)

            !isUsernameValid(form.username) ->
                _signUpForm.value = SignUpFormState(usernameError = R.string.invalid_username)

            !isEmailValid(form.email) ->
                _signUpForm.value = SignUpFormState(emailError = R.string.invalid_email)

            !isPasswordValid(form.password) ->
                _signUpForm.value = SignUpFormState(passwordError = R.string.invalid_password)

            else ->
                _signUpForm.value = SignUpFormState(isDataValid = true)
        }
    }

    // A placeholder name validation check
    private fun isNameValid(name: String): Boolean {
        return name.isNotEmpty()
    }

    // A placeholder username validation check
    private fun isUsernameValid(username: String): Boolean {
        return username.isNotEmpty()
    }

    // A placeholder email validation check
    private fun isEmailValid(email: String): Boolean {
        return if (email.isNotEmpty()) {
            email.matches(EMAIL_ADDRESS_REGEX)
        } else false
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length < 10 && password.isNotBlank()
    }
}