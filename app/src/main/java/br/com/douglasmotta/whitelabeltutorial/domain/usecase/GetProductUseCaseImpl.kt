package br.com.douglasmotta.whitelabeltutorial.domain.usecase

import br.com.douglasmotta.whitelabeltutorial.data.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product

class GetProductUseCaseImpl(
    private val productRepository: ProductRepository
) : GetProductUseCase {

    override suspend fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}