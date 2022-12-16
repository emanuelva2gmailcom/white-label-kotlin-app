package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import br.com.douglasmotta.whitelabeltutorial.domain.model.Product

interface DeleteProductUseCase {

    suspend operator fun invoke(id: String): Product

}