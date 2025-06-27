package com.cemp.feature.auth.domain.usecase.impl

import com.cemp.domain.repository.UserLocalRepository
import com.cemp.feature.auth.domain.usecase.LogoutUseCase

class LogoutUseCaseImpl(
    private val userLocalRepository: UserLocalRepository,
) : LogoutUseCase {
    override suspend fun invoke() {
        userLocalRepository.clearUser()
    }
}