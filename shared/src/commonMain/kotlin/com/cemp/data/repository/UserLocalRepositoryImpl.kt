package com.cemp.data.repository

import com.cemp.common.ext.logErr
import com.cemp.data.mapper.mapToData
import com.cemp.data.mapper.mapToDomain
import com.cemp.data.serializer.UserDataSerializer
import com.cemp.domain.model.local.User
import com.cemp.domain.repository.UserLocalRepository
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@OptIn(ExperimentalSettingsApi::class)
class UserLocalRepositoryImpl(
    private val settings: ObservableSettings,
    private val userDataSerializer: UserDataSerializer,
    private val dispatcher: CoroutineDispatcher,
) : UserLocalRepository {

    override suspend fun saveUser(user: User): Boolean {
        return withContext(dispatcher) {
            runCatching {
                val serialized = userDataSerializer.serialize(
                    user.mapToData()
                )

                settings.putString(USER_DATA, serialized)

                true
            }.getOrElse {
                logErr("Save user to settings exception", it)
                false
            }
        }
    }

    override suspend fun clearUser() {
        withContext(dispatcher) {
            runCatching {
                settings.remove(USER_DATA)
            }.onFailure {
                logErr("Clear user in settings exception", it)
            }
        }
    }

    override fun getUser(): User? {
        return runCatching {
            settings.getStringOrNull(USER_DATA)?.let { serialized ->
                userDataSerializer.deserialize(serialized).mapToDomain()
            }
        }.getOrElse {
            logErr("Get user from settings exception", it)
            null
        }
    }

    override fun getUserFlow(): Flow<User?> {
        return settings.getStringOrNullFlow(USER_DATA).map { serialized ->
            serialized?.let {
                runCatching {
                    userDataSerializer.deserialize(it).mapToDomain()
                }.getOrNull()
            }
        }
    }

    companion object {
        private const val USER_DATA = "user_data"
    }
}
