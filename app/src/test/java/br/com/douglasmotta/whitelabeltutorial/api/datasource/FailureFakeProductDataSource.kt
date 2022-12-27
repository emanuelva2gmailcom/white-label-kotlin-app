package br.com.douglasmotta.whitelabeltutorial.api.datasource

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.api.datasource.product.ProductDataSource
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import java.lang.Exception

class FailureFakeProductDataSource: ProductDataSource {
    override suspend fun getProductById(id: String): Product {
        throw Exception("Error")
    }

    override suspend fun getProducts(): List<Product> {
        throw Exception("Error")
    }

    override suspend fun uploadProductImage(imageUri: Uri): String {
        throw Exception("Error")
    }

    override suspend fun deleteProductImage(imageId: String): Boolean {
        throw Exception("Error")
    }

    override suspend fun updateProductImage(id: String, uri: Uri): String {
        throw Exception("Error")
    }

    override suspend fun createProduct(product: Product): Product {
        throw Exception("Error")
    }

    override suspend fun deleteProduct(id: String): Product {
        throw Exception("Error")
    }

    override suspend fun updateProduct(product: Product): Product {
        throw Exception("Error")
    }
}