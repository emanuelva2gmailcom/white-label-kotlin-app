package br.com.douglasmotta.whitelabeltutorial.ui.auth.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignInUseCase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _statusData = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _statusData

    private val _msgData = MutableLiveData<String>()
    val msg: LiveData<String> = _msgData

    fun signIn(email: String, password: String) {

        if(email.isEmpty() || password.isEmpty()) {
            _msgData.value = "Preencha todos os campos!"
            return
        }

        val task = signInUseCase(email, password);

        task
            .addOnSuccessListener {
                _statusData.value = true
                _msgData.value = "Autenticado com sucesso"
            }
            .addOnFailureListener {
                _msgData.value = try {
                    throw task.exception!!
                } catch (e: FirebaseAuthInvalidUserException) {
                    "Usuário não cadastrado"
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    "Email ou senha não correspondem"
                } catch (e: Exception) {
                    "Erro ao fazer login"
                }
            }
    }
}