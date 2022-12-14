package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.api.repository.ProductRepository
import javax.inject.Inject

class UploadProductImageUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : UploadProductImageUseCase {

    override suspend fun invoke(imageUri: Uri): String {
        return productRepository.uploadProductImage(imageUri)
    }
}