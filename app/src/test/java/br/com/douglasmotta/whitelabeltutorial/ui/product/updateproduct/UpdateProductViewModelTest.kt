package br.com.douglasmotta.whitelabeltutorial.ui.product.updateproduct

import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.api.datasource.FailureFakeProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.datasource.SuccessFakeProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.repository.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.usecase.product.ProductUseCases
import br.com.douglasmotta.whitelabeltutorial.util.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UpdateProductViewModelTest: CoroutineTestCase() {
    lateinit var viewModelFailureTest: UpdateProductViewModel

    lateinit var viewModelSuccessTest: UpdateProductViewModel

    @Before
    fun setupSuccessViewModel() {
        val useCases = ProductUseCases(
            ProductRepository(
                SuccessFakeProductDataSource()
            )
        )

        viewModelSuccessTest = UpdateProductViewModel(useCases.updateProductUseCase)
    }

    @Before
    fun setupFailureViewModel() {
        val useCases = ProductUseCases(
            ProductRepository(
                FailureFakeProductDataSource()
            )
        )

        viewModelFailureTest = UpdateProductViewModel(useCases.updateProductUseCase)
    }


    @Test
    fun `when view model updateProduct get success then sets productUpdatedLiveData`() = runBlocking {

        // Act
        viewModelSuccessTest.updateProduct(
            PRODUCT1_REF.id,
            PRODUCT1_REF.description,
            PRODUCT1_REF.price.toCurrency(),
            PRODUCT_IMAGE_URI_REF
        )

        // Assert
        Assert.assertEquals(viewModelSuccessTest.productUpdated.getOrAwaitValue(), PRODUCT1_REF)
    }

    @Test
    fun `when view model updateProduct get failure then sets productUpdateErrorResIdLiveData`() =
        runBlocking {

            // Act
            viewModelFailureTest.updateProduct(
                PRODUCT1_REF.id,
                PRODUCT1_REF.description,
                PRODUCT1_REF.price.toCurrency(),
                PRODUCT_IMAGE_URI_REF
            )

            // Assert
            Assert.assertEquals(
                viewModelFailureTest.productUpdateErrorResId.getOrAwaitValue(),
                R.string.error_message_updateProduct
            )
        }

    @Test
    fun `when view model updateProduct get empty error in description then sets descriptionFieldErrorResIdLiveData`()
        = runBlocking {

            // Act
            viewModelFailureTest.updateProduct(
                PRODUCT1_REF.id,
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
    fun `when view model updateProduct get empty error in price then sets priceFieldErrorResIdLiveData`()
        = runBlocking {

            // Act
            viewModelFailureTest.updateProduct(
                PRODUCT1_REF.id,
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