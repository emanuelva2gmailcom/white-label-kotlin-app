package br.com.douglasmotta.whitelabeltutorial.api.datasource.product

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product

interface ProductDataSource {

    suspend fun getProducts(): List<Product>

    suspend fun uploadProductImage(imageUri: Uri): String

    suspend fun createProduct(product: Product): Product

    suspend fun deleteProduct(id: String): Boolean

    suspend fun updateProduct(product: Product): Product

}