package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import br.com.douglasmotta.whitelabeltutorial.api.datasource.SuccessFakeProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.repository.ProductRepository

class ProductUseCases(
    private val repository: ProductRepository
) {

    val getProductUseCase = GetProductUseCaseImpl(repository)

    val uploadProductImageUseCase = UploadProductImageUseCaseImpl(repository)

    val updateProductImageUseCase = UpdateProductImageUseCaseImpl(repository)

    val deleteProductImageUseCase = DeleteProductImageUseCaseImpl(repository)

    val createProductUseCase = CreateProductUseCaseImpl(uploadProductImageUseCase, repository)

    val updateProductUseCase = UpdateProductUseCaseImpl(repository, updateProductImageUseCase)

    val deleteProductUseCase = DeleteProductUseCaseImpl(repository, deleteProductImageUseCase)
}
