package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import android.net.Uri

interface UpdateProductImageUseCase {

    suspend operator fun invoke(id: String, uri: Uri): String
}