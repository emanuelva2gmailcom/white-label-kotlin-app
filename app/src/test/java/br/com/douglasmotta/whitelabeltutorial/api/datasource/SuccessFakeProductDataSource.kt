package br.com.douglasmotta.whitelabeltutorial.api.datasource

import android.net.Uri
import br.com.douglasmotta.whitelabeltutorial.api.datasource.product.ProductDataSource
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import br.com.douglasmotta.whitelabeltutorial.util.*

class SuccessFakeProductDataSource: ProductDataSource {
    override suspend fun getProductById(id: String): Product {
        return PRODUCT1_REF
    }

    override suspend fun getProducts(): List<Product> {
        return PRODUCT_LIST_REF
    }

    override suspend fun uploadProductImage(imageUri: Uri): String {
        return PRODUCT_IMAGE_URL_REF
    }

    override suspend fun deleteProductImage(imageId: String): Boolean {
        return true
    }

    override suspend fun updateProductImage(id: String, uri: Uri): String {
        return PRODUCT_IMAGE_URL_REF
    }

    override suspend fun createProduct(product: Product): Product {
        return PRODUCT1_REF
    }

    override suspend fun deleteProduct(id: String): Product {
        return PRODUCT1_REF
    }

    override suspend fun updateProduct(product: Product): Product {
        return PRODUCT1_REF
    }
}