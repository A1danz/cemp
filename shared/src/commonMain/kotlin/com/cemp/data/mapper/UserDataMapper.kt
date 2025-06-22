package com.cemp.data.mapper

import com.cemp.data.settings.model.UserData
import com.cemp.domain.model.local.User

fun UserData.mapToDomain(): User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
    )
}

fun User.mapToData(): UserData {
    return UserData(
        id = id,
        name = name,
        username = username,
        email = email,
    )
}