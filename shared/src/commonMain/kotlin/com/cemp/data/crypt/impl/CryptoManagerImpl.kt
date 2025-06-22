package com.cemp.data.crypt.impl

import com.cemp.data.crypt.CryptoManager

// todo: Add KMP implementation of CryptoManager
class CryptoManagerImpl : CryptoManager {
    override fun cryptPassword(password: String): String {
        return password
    }

    override fun verifyPassword(password: String, passwordHash: String): Boolean {
        return password == passwordHash
    }
}