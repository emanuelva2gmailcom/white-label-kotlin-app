package br.com.douglasmotta.whitelabeltutorial.domain.usecase

interface DeleteProductUseCase {

    suspend operator fun invoke(id: String): Boolean

}