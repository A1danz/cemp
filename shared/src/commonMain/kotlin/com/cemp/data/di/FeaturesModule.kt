package com.cemp.data.di

import com.cemp.feature.leaderboard.domain.usecase.GetTeamsLeaderboardUseCase
import com.cemp.feature.leaderboard.domain.usecase.impl.GetTeamsLeaderboardUseCaseImpl
import com.cemp.feature.matches.domain.usecase.GetMatchesUseCase
import com.cemp.feature.matches.domain.usecase.impl.GetMatchesUseCaseImpl
import com.cemp.feature.teams.domain.usecase.GetTeamUseCase
import com.cemp.feature.teams.domain.usecase.impl.GetTeamUseCaseImpl
import org.koin.dsl.module

val featuresModule = module {
    single<GetTeamUseCase> { GetTeamUseCaseImpl(get(), get()) }
    single<GetMatchesUseCase> { GetMatchesUseCaseImpl(get(), get()) }
    single<GetTeamsLeaderboardUseCase> { GetTeamsLeaderboardUseCaseImpl(get(), get()) }
}