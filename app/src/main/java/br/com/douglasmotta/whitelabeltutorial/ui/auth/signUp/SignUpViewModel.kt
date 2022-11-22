package br.com.douglasmotta.whitelabeltutorial.ui.auth.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignUpUseCase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _statusData = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _statusData

    private val _msgData = MutableLiveData<String>()
    val msg: LiveData<String> = _msgData

    fun signUp(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            _msgData.value = "Preencha todos os campos!"
            return
        }

        val task = signUpUseCase(email, password)

        task.addOnSuccessListener {
            _msgData.value = "Usuário cadastrado com sucesso!"
            _statusData.value = true
        }
            .addOnFailureListener{
                _msgData.value = try {
                    throw task.exception!!
                } catch (e: FirebaseAuthWeakPasswordException) {
                    "Digite uma senha mais forte"
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    "Digite um email válido"
                } catch (e: FirebaseAuthUserCollisionException) {
                    "Email já cadastrado"
                } catch (e: Exception) {
                    "Erro ao cadastrar usuário"
                }
            }

    }
}