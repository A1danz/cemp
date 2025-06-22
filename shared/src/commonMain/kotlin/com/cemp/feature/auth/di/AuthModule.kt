package com.cemp.feature.auth.di

import com.cemp.feature.auth.data.repository.AuthRepositoryImpl
import com.cemp.feature.auth.domain.repository.AuthRepository
import com.cemp.feature.auth.domain.usecase.LoginUseCase
import com.cemp.feature.auth.domain.usecase.RegistrationUseCase
import com.cemp.feature.auth.domain.usecase.impl.LoginUseCaseImpl
import com.cemp.feature.auth.domain.usecase.impl.RegistrationUseCaseImpl
import com.cemp.feature.auth.domain.validator.UserCredentialsValidator
import com.cemp.feature.auth.domain.validator.impl.UserCredentialsValidatorImpl
import org.koin.dsl.module

val authModule = module {
    single<UserCredentialsValidator> { UserCredentialsValidatorImpl() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get(), get()) }
    single<LoginUseCase> { LoginUseCaseImpl(get(), get()) }
    single<RegistrationUseCase> { RegistrationUseCaseImpl(get(), get()) }
}