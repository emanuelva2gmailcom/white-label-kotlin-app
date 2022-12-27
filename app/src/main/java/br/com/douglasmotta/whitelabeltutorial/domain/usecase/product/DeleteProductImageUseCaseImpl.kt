package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import br.com.douglasmotta.whitelabeltutorial.api.repository.ProductRepository
import javax.inject.Inject

class DeleteProductImageUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : DeleteProductImageUseCase {

    override suspend fun invoke(imageId: String): Boolean {
        return productRepository.deleteProductImage(imageId)
    }
}