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

    private val _resultData = MutableLiveData<Result<Boolean>>()
    val resultData: LiveData<Result<Boolean>> = _resultData

    fun logout() {
        try {
            signOutUseCase()
            _resultData.value = Result.Success(true)
        } catch (e: Exception) {
            _resultData.value = Result.Error(e)
        }
    }
}