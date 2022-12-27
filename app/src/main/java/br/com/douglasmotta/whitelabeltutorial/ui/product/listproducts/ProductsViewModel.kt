package br.com.douglasmotta.whitelabeltutorial.ui.product.listproducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.config.Config
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.product.DeleteProductUseCase
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.product.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    config: Config,
) : ViewModel() {

    private val _productsData = MutableLiveData<List<Product>>()
    val productsData: LiveData<List<Product>> = _productsData

    private val _addMutableVisibilityData = MutableLiveData(config.addButtonBoolean)
    val addMutableVisibilityData: LiveData<Int> = _addMutableVisibilityData

    private val _deleteIdData = MutableLiveData<String>()
    val deleteIdData: LiveData<String> = _deleteIdData

    private val _errorData = MutableLiveData<Int?>(null)
    val errorData: LiveData<Int?> = _errorData

    val mutableUpdateProductData = MutableLiveData<Product>()

    fun getProducts() = viewModelScope.launch {
        try {
            val products = getProductUseCase()
            _productsData.value = products
            _errorData.value = null
        } catch (e: Exception) {
            _errorData.value = R.string.error_message_getProducts
        }
    }

    fun deleteProduct(id: String) = viewModelScope.launch {
        try {
            deleteProductUseCase(id)
            _deleteIdData.value = id
            _errorData.value = null
        } catch (e: Exception) {
            _errorData.value = R.string.error_message_deleteProduct
        }
    }
}