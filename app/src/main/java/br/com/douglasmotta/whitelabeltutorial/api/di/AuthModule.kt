package br.com.douglasmotta.whitelabeltutorial.api.di

import br.com.douglasmotta.whitelabeltutorial.api.auth.AuthService
import br.com.douglasmotta.whitelabeltutorial.api.auth.FirebaseAuthService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Singleton
    @Binds
    fun bindAuthService(authService: FirebaseAuthService): AuthService
}