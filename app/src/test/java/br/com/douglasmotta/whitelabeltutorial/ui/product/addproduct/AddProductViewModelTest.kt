package br.com.douglasmotta.whitelabeltutorial.ui.product.addproduct

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.api.datasource.FailureFakeProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.datasource.SuccessFakeProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.repository.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.product.ProductUseCases
import br.com.douglasmotta.whitelabeltutorial.util.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddProductViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModelFailureTest: AddProductViewModel

    lateinit var viewModelSuccessTest: AddProductViewModel

    @Before
    fun setupSuccessViewModel() {
        val useCases = ProductUseCases(
            ProductRepository(
                SuccessFakeProductDataSource()
            )
        )

        viewModelSuccessTest = AddProductViewModel(useCases.createProductUseCase)
    }

    @Before
    fun setupFailureViewModel() {
        val useCases = ProductUseCases(
            ProductRepository(
                FailureFakeProductDataSource()
            )
        )

        viewModelFailureTest = AddProductViewModel(useCases.createProductUseCase)
    }


    @Test
    fun `when view model createProduct get success then sets productCreatedLiveData`() = runBlocking {

        // Act
        viewModelSuccessTest.createProduct(
            PRODUCT1_REF.description,
            PRODUCT1_REF.price.toCurrency(),
            PRODUCT_IMAGE_URI_REF
        )

        // Assert
        Assert.assertEquals(viewModelSuccessTest.productCreated.getOrAwaitValue(), PRODUCT1_REF)
    }

    @Test
    fun `when view model createProduct get failure then sets productCreateErrorResIdLiveData`() =
        runBlocking {

            // Act
            viewModelFailureTest.createProduct(
                PRODUCT1_REF.description,
                PRODUCT1_REF.price.toCurrency(),
                PRODUCT_IMAGE_URI_REF
            )

            // Assert
            Assert.assertEquals(
                viewModelFailureTest.productCreateErrorResId.getOrAwaitValue(),
                R.string.error_message_addProduct
            )
        }

    @Test
    fun `when view model createProduct get empty error in imageUri then sets imageUriErrorResIdLiveData`()
        = runBlocking {

            // Act
            viewModelFailureTest.createProduct(
                PRODUCT1_REF.description,
                PRODUCT1_REF.price.toCurrency(),
                null
            )

            // Assert
            Assert.assertEquals(
                viewModelFailureTest.imageUriErrorResId.getOrAwaitValue(),
                R.drawable.background_product_image_error
            )
        }

    @Test
    fun `when view model createProduct get empty error in description then sets descriptionFieldErrorResIdLiveData`()
        = runBlocking {

            // Act
            viewModelFailureTest.createProduct(
                "",
                PRODUCT1_REF.price.toCurrency(),
                PRODUCT_IMAGE_URI_REF
            )

            // Assert
            Assert.assertEquals(
                viewModelFailureTest.descriptionFieldErrorResId.getOrAwaitValue(),
                R.string.add_product_field_error
            )
        }

    @Test
    fun `when view model createProduct get empty error in price then sets priceFieldErrorResIdLiveData`()
        = runBlocking {

            // Act
            viewModelFailureTest.createProduct(
                PRODUCT1_REF.description,
                "",
                PRODUCT_IMAGE_URI_REF
            )

            // Assert
            Assert.assertEquals(
                viewModelFailureTest.priceFieldErrorResId.getOrAwaitValue(),
                R.string.add_product_field_error
            )
        }
}