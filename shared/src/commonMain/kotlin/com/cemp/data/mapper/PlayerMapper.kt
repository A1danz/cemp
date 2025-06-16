package com.cemp.data.mapper

import com.cemp.data.network.response.PlayerResponse
import com.cemp.domain.model.Country
import com.cemp.domain.model.Player

fun PlayerResponse.toPlayer(): Player {
    return Player(
        id = id,
        name = name,
        firstName = firstName,
        secondName = lastName,
        nationality = nationality?.let { Country.getByCountryCode(nationality) }
    )
}