package br.com.douglasmotta.whitelabeltutorial.domain.usecase.di

import br.com.douglasmotta.whitelabeltutorial.domain.usecase.auth.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainAuthModule {

    @Binds
    fun bindSignUpUseCase(useCaseImpl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    fun bindSignInUseCase(useCaseImpl: SignInUseCaseImpl): SignInUseCase

    @Binds
    fun bindSignOutUseCase(useCaseImpl: SignOutUseCaseImpl): SignOutUseCase

    @Binds
    fun bindGetUserAuthUseCase(useCaseImpl: GetUserAuthUseCaseImpl): GetUserAuthUseCase
}