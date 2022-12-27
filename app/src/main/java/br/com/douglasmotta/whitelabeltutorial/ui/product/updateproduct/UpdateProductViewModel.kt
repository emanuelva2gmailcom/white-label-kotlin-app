package br.com.douglasmotta.whitelabeltutorial.ui.product.updateproduct

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.product.UpdateProductUseCase
import br.com.douglasmotta.whitelabeltutorial.util.fromCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProductViewModel @Inject constructor(
    private val updateProductUseCase: UpdateProductUseCase
) : ViewModel() {

    private val _imageUriErrorResId = MutableLiveData<Int>()
    val imageUriErrorResId: LiveData<Int> = _imageUriErrorResId

    private val _descriptionFieldErrorResId = MutableLiveData<Int?>()
    val descriptionFieldErrorResId: LiveData<Int?> = _descriptionFieldErrorResId

    private val _priceFieldErrorResId = MutableLiveData<Int?>()
    val priceFieldErrorResId: LiveData<Int?> = _priceFieldErrorResId

    private val _productUpdateErrorResId = MutableLiveData<Int?>()
    val productUpdateErrorResId: LiveData<Int?> = _productUpdateErrorResId

    private val _productUpdated = MutableLiveData<Product>()
    val productUpdated: LiveData<Product> = _productUpdated

    private var isFormValid = false

    fun updateProduct(id: String, description: String, price: String, imageUri: Uri?) = viewModelScope.launch {
        isFormValid = true

        _descriptionFieldErrorResId.value = getErrorStringResIdIfEmpty(description)
        _priceFieldErrorResId.value = getErrorStringResIdIfEmpty(price)

        if (isFormValid) {
            try {
                val product = updateProductUseCase(id, description, price.fromCurrency(), imageUri)
                _productUpdated.value = product
            } catch (e: Exception) {
                _productUpdateErrorResId.value = R.string.error_message_updateProduct
            }
        }
    }

    private fun getErrorStringResIdIfEmpty(value: String): Int? {
        return if (value.isEmpty()) {
            isFormValid = false
            R.string.add_product_field_error
        } else null
    }
}
