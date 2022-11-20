package br.com.douglasmotta.whitelabeltutorial.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.douglasmotta.whitelabeltutorial.config.Config
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.DeleteProductUseCase
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    config: Config
) : ViewModel() {

    private val _productsData = MutableLiveData<List<Product>>()
    val productsData: LiveData<List<Product>> = _productsData

    private val _addMutableVisibilityData = MutableLiveData(config.addButtonBoolean)
    val addMutableVisibilityData: LiveData<Int> = _addMutableVisibilityData

    private val _deleteIdData = MutableLiveData<String>()
    val deleteIdData: LiveData<String> = _deleteIdData

    val mutableUpdateProductData = MutableLiveData<Product>()

    fun getProducts() = viewModelScope.launch {
        try {
            val products = getProductUseCase()
            _productsData.value = products
        } catch (e: Exception) {
            Log.d("ProductsViewModel", e.toString())
        }
    }

    fun deleteProduct(id: String) = viewModelScope.launch {
        try {
            deleteProductUseCase(id)
            _deleteIdData.value = id
        } catch (e: Exception) {
            Log.d("ProductsViewModel", e.toString())
        }
    }
}