package com.cemp.feature.auth.data.repository

import com.cemp.common.ext.logErr
import com.cemp.common.ext.transform
import com.cemp.data.crypt.CryptoManager
import com.cemp.domain.model.local.User
import com.cemp.domain.repository.UserLocalRepository
import com.cemp.feature.auth.domain.model.error.LoginError
import com.cemp.feature.auth.domain.model.error.RegistrationError
import com.cemp.feature.auth.domain.model.result.LoginResult
import com.cemp.feature.auth.domain.model.result.RegistrationResult
import com.cemp.feature.auth.domain.repository.AuthRepository
import com.cemp.shared.data.database.user.UserQueries
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val userQueries: UserQueries,
    private val dispatcher: CoroutineDispatcher,
    private val cryptoManager: CryptoManager,
    private val userLocalRepository: UserLocalRepository,
) : AuthRepository {
    override suspend fun login(email: String, password: String): LoginResult {
        return withContext(dispatcher) {
            userQueries.findByEmail(email).executeAsOneOrNull()?.let { user ->
                cryptoManager.verifyPassword(
                    password = password,
                    passwordHash = user.password_hash,
                ).let { verifyResult ->
                    if (verifyResult) {
                        val saveInSessionResult = saveUserSessionInStorage(user)

                        if (saveInSessionResult) {
                            LoginResult.Success
                        } else {
                            LoginResult.Failed(LoginError.Unknown)
                        }
                    } else LoginResult.Failed(error = LoginError.IncorrectPassword)
                }
            } ?: LoginResult.Failed(error = LoginError.UserNotFound)
        }
    }

    override suspend fun register(
        name: String,
        username: String,
        email: String,
        password: String
    ): RegistrationResult {
        return withContext(dispatcher) {
            if (userQueries.findByEmailOrUsername(email, username).executeAsOneOrNull() == null) {
                // insert user and then find it in db and save in session
                userQueries.insertUser(name, username, email, cryptoManager.cryptPassword(password))

                val lastInsertedId = userQueries.findByEmail(email).executeAsOneOrNull()
                    ?: return@withContext RegistrationResult.Failed(RegistrationError.Unknown)
                        .also {
                            logErr("Last inserted id is null, can't register user")
                        }

                val user = userQueries.findById(id = lastInsertedId.id).executeAsOneOrNull()
                    ?: return@withContext RegistrationResult.Failed(RegistrationError.Unknown)
                        .also {
                            logErr("User by lastInsertedId[$lastInsertedId] = null, can't register user")
                        }

                return@withContext saveUserSessionInStorage(user).transform(
                    onTrue = RegistrationResult.Success,
                    onFalse = RegistrationResult.Failed(RegistrationError.Unknown)
                )
            } else {
                RegistrationResult.Failed(error = RegistrationError.UserAlreadyExists)
            }
        }
    }

    private suspend fun saveUserSessionInStorage(user: com.cemp.shared.data.database.user.User): Boolean {
        return userLocalRepository.saveUser(
            User(
                id = user.id,
                name = user.name,
                username = user.username,
                email = user.email,
            )
        )
    }
}