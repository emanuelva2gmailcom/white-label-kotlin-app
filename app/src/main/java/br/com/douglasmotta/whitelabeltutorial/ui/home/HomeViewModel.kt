package br.com.douglasmotta.whitelabeltutorial.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.GetUserAuthUseCase
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignOutUseCase
import br.com.douglasmotta.whitelabeltutorial.ui.BaseViewModel
import com.google.firebase.firestore.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserAuthUseCase: GetUserAuthUseCase
) : BaseViewModel() {

    private val _errorMessageData = MutableLiveData<Int?>(null)
    val errorMessageData: LiveData<Int?> = _errorMessageData

    private val _userInformationData = MutableLiveData<String>()
    val userInformationData: LiveData<String> = _userInformationData

    fun getUserEmail() {
        try {
            _userInformationData.value = getUserAuthUseCase().email
        } catch (e: Exception) {
            _errorMessageData.value = R.string.error_message_default
        }
    }
}