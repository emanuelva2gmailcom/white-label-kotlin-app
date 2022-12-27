package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import android.util.Log
import br.com.douglasmotta.whitelabeltutorial.api.repository.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val deleteProductImageUseCase: DeleteProductImageUseCase
) : DeleteProductUseCase {

    override suspend operator fun invoke(id: String): Product {
        return try {
            val product = productRepository.deleteProduct(id)
            deleteProductImageUseCase(product.imageUrl)

            product
        } catch (e: Exception) {
            Log.d("deleteimageusecase", e.toString())
            throw e
        }
    }

}