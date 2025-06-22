package com.cemp.data.serializer

import com.cemp.data.settings.model.UserData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserDataSerializer(
    private val json: Json,
) {
    fun serialize(user: UserData): String = json.encodeToString(user)
    fun deserialize(jsonString: String): UserData = json.decodeFromString(jsonString)
}