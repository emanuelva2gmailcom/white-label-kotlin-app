package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import br.com.douglasmotta.whitelabeltutorial.api.datasource.product.ProductRepository
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : DeleteProductUseCase {

    override suspend operator fun invoke(id: String): Boolean {
        return productRepository.deleteProduct(id)
    }

}