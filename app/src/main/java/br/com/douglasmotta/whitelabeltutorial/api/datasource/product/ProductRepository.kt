package br.com.douglasmotta.whitelabeltutorial.api.datasource.product

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val dataSource: ProductDataSource
    ) {

    suspend fun getProducts(): List<Product> = dataSource.getProducts()

    suspend fun uploadProductImage(imageUri: Uri): String = dataSource.uploadProductImage(imageUri)

    suspend fun createProduct(product: Product): Product = dataSource.createProduct(product)

    suspend fun deleteProduct(id: String): Boolean = dataSource.deleteProduct(id)

    suspend fun updateProduct(product: Product): Product = dataSource.updateProduct(product)

}