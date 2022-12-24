package br.com.douglasmotta.whitelabeltutorial.domain.usecase.product

import br.com.douglasmotta.whitelabeltutorial.api.datasource.SuccessFakeProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.repository.ProductRepository

class ProductUseCases constructor(
    repository: ProductRepository
) {

    val getProductUseCase = GetProductUseCaseImpl(repository)

    private val uploadProductImageUseCase = UploadProductImageUseCaseImpl(repository)

    private val updateProductImageUseCase = UpdateProductImageUseCaseImpl(repository)

    private val deleteProductImageUseCase = DeleteProductImageUseCaseImpl(repository)

    val createProductUseCase = CreateProductUseCaseImpl(uploadProductImageUseCase, repository)

    val updateProductUseCase = UpdateProductUseCaseImpl(repository, updateProductImageUseCase)

    val deleteProductUseCase = DeleteProductUseCaseImpl(repository, deleteProductImageUseCase)
}
