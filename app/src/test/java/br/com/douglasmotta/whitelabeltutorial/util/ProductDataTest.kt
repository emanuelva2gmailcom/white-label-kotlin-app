package br.com.douglasmotta.whitelabeltutorial.util

import br.com.douglasmotta.whitelabeltutorial.domain.model.Product

val PRODUCT_ID_REF = "1"

val PRODUCT1_REF = Product(
    "1",
    "Description 1",
    1.0,
    "localhost:1234/api/imagemproduct/1"
)

val PRODUCT2_REF = Product(
    "2",
    "Description 2",
    2.0,
    "localhost:1234/api/imagemproduct/1"
)

val PRODUCT_LIST_REF = listOf(PRODUCT1_REF, PRODUCT2_REF)

val PRODUCT_IMAGE_URL_REF = "localhost:1234/api/imagemproduct/1"
