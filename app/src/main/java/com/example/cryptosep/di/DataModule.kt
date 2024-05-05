package com.example.cryptosep.di

import com.example.cryptosep.data.remote.KucoinApiService
import com.example.cryptosep.data.repository.KucoinRepositoryImpl
import com.example.cryptosep.data.utils.NetworkConnection
import com.example.cryptosep.domain.repository.KucoinRepository
import com.example.cryptosep.domain.usecase.CurrencyListUseCase
import com.example.cryptosep.domain.usecase.FetchTickerUseCase
import com.example.cryptosep.domain.usecase.MarketListUseCase
import com.example.cryptosep.domain.usecase.TickerListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideKucoinRepository(
        api: KucoinApiService,
        networkConnection: NetworkConnection
    ): KucoinRepository =
        KucoinRepositoryImpl(api, networkConnection)

    @Provides
    fun provideCurrencyUseCase(repository: KucoinRepository): CurrencyListUseCase =
        CurrencyListUseCase(repository)

    @Provides
    fun provideTickerUseCase(repository: KucoinRepository): FetchTickerUseCase =
        FetchTickerUseCase(repository)

    @Provides
    fun provideMarketUseCase(repository: KucoinRepository): MarketListUseCase =
        MarketListUseCase(repository)

    @Provides
    fun provideTickerListUseCase(repository: KucoinRepository): TickerListUseCase =
        TickerListUseCase(repository)

}