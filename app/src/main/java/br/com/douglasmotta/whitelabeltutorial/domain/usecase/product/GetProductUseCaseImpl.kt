package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import br.com.douglasmotta.whitelabeltutorial.data.ProductRepository
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import javax.inject.Inject

class GetProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductUseCase {

    override suspend fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}