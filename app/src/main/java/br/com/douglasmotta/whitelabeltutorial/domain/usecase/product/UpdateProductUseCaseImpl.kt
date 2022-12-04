package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import android.net.Uri
import android.util.Log
import br.com.douglasmotta.whitelabeltutorial.api.datasource.product.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import java.lang.Exception
import javax.inject.Inject

class UpdateProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val updateProductImageUseCase: UpdateProductImageUseCase
) : UpdateProductUseCase {

    override suspend fun invoke(id: String, description: String, price: Double, imageUri: Uri?): Product {
        return try {
            val product = Product(id, description, price)

            if (imageUri != null) {
                product.imageUrl = updateProductImageUseCase(id, imageUri)
            }

            productRepository.updateProduct(product)
        } catch(e: Exception) {
            throw e
        }
    }
}