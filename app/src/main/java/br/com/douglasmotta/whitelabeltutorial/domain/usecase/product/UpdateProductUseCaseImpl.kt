package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import android.net.Uri
import android.util.Log
import br.com.douglasmotta.whitelabeltutorial.data.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import java.lang.Exception
import javax.inject.Inject

class UpdateProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val uploadProductImageUseCase: UploadProductImageUseCase
) : UpdateProductUseCase {

    override suspend fun invoke(id: String, description: String, price: Double, imageUri: Uri?): Product {
        return try {
            val product = Product(id, description, price)
            Log.d(null, imageUri.toString())
            if (imageUri != null) {
                product.imageUrl = uploadProductImageUseCase(imageUri)
            }
            productRepository.updateProduct(product)
        } catch(e: Exception) {
            throw e
        }
    }
}