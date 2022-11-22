package br.com.douglasmotta.whitelabeltutorial.domain.usecase.di

import br.com.douglasmotta.whitelabeltutorial.domain.usecase.product.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainProductModule {

    @Binds
    fun bindCreateProductUseCase(useCaseImpl: CreateProductUseCaseImpl): CreateProductUseCase

    @Binds
    fun bindGetProductsUseCase(useCaseImpl: GetProductUseCaseImpl): GetProductUseCase

    @Binds
    fun bindUploadImageUseCase(useCaseImpl: UploadProductImageUseCaseImpl): UploadProductImageUseCase

    @Binds
    fun bindDeleteProductUseCase(useCaseImpl: DeleteProductUseCaseImpl): DeleteProductUseCase

    @Binds
    fun bindUpdateProductUseCase(useCaseImpl: UpdateProductUseCaseImpl): UpdateProductUseCase
}