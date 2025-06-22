package com.cemp.domain.repository

import com.cemp.domain.model.local.User
import kotlinx.coroutines.flow.Flow

interface UserLocalRepository {
    suspend fun saveUser(user: User): Boolean
    suspend fun getUser(): User?
    suspend fun clearUser()
    fun getUserFlow(): Flow<User?>
}