package br.com.douglasmotta.whitelabeltutorial.util

import java.util.regex.Pattern

const val COLLECTION_ROOT = "data"
const val COLLECTION_PRODUCTS = "products"
const val COLLECTION_USERS = "users"
const val STORAGE_IMAGES = "images"
const val PRODUCT_KEY = "product"
val EMAIL_ADDRESS_REGEX = Regex(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)