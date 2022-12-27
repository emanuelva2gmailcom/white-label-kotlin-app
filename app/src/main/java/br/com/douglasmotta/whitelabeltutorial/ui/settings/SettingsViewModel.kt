package br.com.douglasmotta.whitelabeltutorial.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.SignOutUseCase
import br.com.douglasmotta.whitelabeltutorial.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _errorResultData = MutableLiveData<Result<Boolean>>()
    val errorResultData: LiveData<Result<Boolean>> = _errorResultData

    fun logout() {
        try {
            signOutUseCase()
            _errorResultData.value = Result.Success(true)
        } catch (e: Exception) {
            _errorResultData.value = Result.Error(e)
        }
    }
}