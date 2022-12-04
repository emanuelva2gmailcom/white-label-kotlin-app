package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.api.datasource.product.ProductRepository
import javax.inject.Inject

class UpdateProductImageUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : UpdateProductImageUseCase {

    override suspend fun invoke(id: String, uri: Uri): String {
        return productRepository.updateProductImage(id, uri)
    }
}