package com.cemp.data.crypt

interface CryptoManager {
    fun cryptPassword(password: String): String
    fun verifyPassword(password: String, passwordHash: String): Boolean
}