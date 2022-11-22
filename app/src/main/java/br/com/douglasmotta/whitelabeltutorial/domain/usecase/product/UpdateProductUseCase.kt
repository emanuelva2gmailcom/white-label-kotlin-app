package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product

interface UpdateProductUseCase {

    suspend operator fun invoke(id: String, description: String, price: Double, imageUri: Uri?): Product
}