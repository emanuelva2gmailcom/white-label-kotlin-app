package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

interface DeleteProductImageUseCase {

    suspend operator fun invoke(imageId: String): Boolean
}