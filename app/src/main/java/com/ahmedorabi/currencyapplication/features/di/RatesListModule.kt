package com.ahmedorabi.currencyapplication.features.di

import com.ahmedorabi.currencyapplication.core.data.api.ApiService
import com.ahmedorabi.currencyapplication.core.domain.usecases.GetRatesUseCase
import com.ahmedorabi.currencyapplication.core.repo.CurrencyDataSource
import com.ahmedorabi.currencyapplication.core.repo.CurrencyRepository
import com.ahmedorabi.currencyapplication.features.rates_list.framework.ApiRatesListDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RatesListModule {

    @Singleton
    @Provides
    fun provideInApiRatesListDataSource(apiService: ApiService): CurrencyDataSource {
        return ApiRatesListDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideRatesRepository(ratesDataSource: CurrencyDataSource): CurrencyRepository {
        return CurrencyRepository(ratesDataSource)
    }


    @Singleton
    @Provides
    fun provideUseCase(ratesRepository: CurrencyRepository): GetRatesUseCase {
        return GetRatesUseCase(ratesRepository)
    }


}