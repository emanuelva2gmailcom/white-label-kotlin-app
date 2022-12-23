package br.com.douglasmotta.whitelabeltutorial.ui.product.listproducts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.api.datasource.FailureFakeProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.datasource.SuccessFakeProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.repository.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.product.ProductUseCases
import br.com.douglasmotta.whitelabeltutorial.util.*
import kotlinx.coroutines.runBlocking
import org.junit.*

class ProductsViewModelTests {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var config = ConfigTest()

    // Get Product
    @Test
    fun `when view model getProducts get success then sets productsLiveData`(): Unit = runBlocking {
        // Arrage
        val useCases = ProductUseCases(ProductRepository(SuccessFakeProductDataSource()))
        val viewModel = ProductsViewModel(
            useCases.getProductUseCase,
            useCases.deleteProductUseCase,
            config
        )

        // act
        viewModel.getProducts()

        // Assert
        Assert.assertEquals(viewModel.productsData.getOrAwaitValue(), PRODUCT_LIST_REF)
    }

    @Test
    fun `when view model getProducts get failure then sets errorLiveData`(): Unit = runBlocking {
        // Arrange
        val useCases = ProductUseCases(ProductRepository(FailureFakeProductDataSource()))
        val viewModel = ProductsViewModel(
            useCases.getProductUseCase,
            useCases.deleteProductUseCase,
            config
        )

        // act
        viewModel.getProducts()

        // Assert
        Assert.assertEquals(viewModel.errorData.getOrAwaitValue(), R.string.error_message_getProducts)
    }

    // Delete Product
    @Test
    fun `when view model deleteProduct get success then sets deleteIdLiveData`(): Unit = runBlocking {
        // Arrage
        val useCases = ProductUseCases(ProductRepository(SuccessFakeProductDataSource()))
        val viewModel = ProductsViewModel(
            useCases.getProductUseCase,
            useCases.deleteProductUseCase,
            config
        )

        // act
        viewModel.deleteProduct(PRODUCT_ID_REF)

        // Assert
        Assert.assertEquals(viewModel.deleteIdData.getOrAwaitValue(), PRODUCT_ID_REF)
    }

    @Test
    fun `when view model deleteProduct get failure then sets errorLiveData`(): Unit = runBlocking {
        // Arrange
        val useCases = ProductUseCases(ProductRepository(FailureFakeProductDataSource()))
        val viewModel = ProductsViewModel(
            useCases.getProductUseCase,
            useCases.deleteProductUseCase,
            config
        )

        // act
        viewModel.deleteProduct(PRODUCT_ID_REF)

        // Assert
        Assert.assertEquals(viewModel.errorData.getOrAwaitValue(), R.string.error_message_deleteProduct)
    }
}