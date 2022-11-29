package br.com.douglasmotta.whitelabeltutorial.api.di

import br.com.douglasmotta.whitelabeltutorial.api.datasource.product.FirebaseProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.datasource.product.ProductDataSource
import br.com.douglasmotta.whitelabeltutorial.api.datasource.user.FirebaseUserDataSource
import br.com.douglasmotta.whitelabeltutorial.api.datasource.user.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindProductDataSource(dataSource: FirebaseProductDataSource): ProductDataSource

    @Singleton
    @Binds
    fun bindUserDataSource(dataSource: FirebaseUserDataSource): UserDataSource
}