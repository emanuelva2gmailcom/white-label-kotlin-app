package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

interface DeleteProductUseCase {

    suspend operator fun invoke(id: String): Boolean

}