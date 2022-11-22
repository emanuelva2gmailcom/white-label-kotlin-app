package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product

interface GetProductUseCase {

    suspend operator fun invoke(): List<Product>

}