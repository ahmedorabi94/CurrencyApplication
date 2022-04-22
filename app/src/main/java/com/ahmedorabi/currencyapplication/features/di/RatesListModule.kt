package com.ahmedorabi.currencyapplication.features.di

import com.ahmedorabi.currencyapplication.core.data.api.ApiService
import com.ahmedorabi.currencyapplication.core.db.CurrencyDao
import com.ahmedorabi.currencyapplication.core.domain.usecases.AddRateUseCase
import com.ahmedorabi.currencyapplication.core.domain.usecases.GetRatesLocalUseCase
import com.ahmedorabi.currencyapplication.core.domain.usecases.GetRatesUseCase
import com.ahmedorabi.currencyapplication.core.repo.CurrencyDataSource
import com.ahmedorabi.currencyapplication.core.repo.CurrencyRepository
import com.ahmedorabi.currencyapplication.core.repo.RoomDataSource
import com.ahmedorabi.currencyapplication.core.repo.RoomRepository
import com.ahmedorabi.currencyapplication.features.rates_list.framework.ApiRatesListDataSource
import com.ahmedorabi.currencyapplication.features.rates_list.framework.InRoomLocalDataSourceAddRate
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



    @Singleton
    @Provides
    fun provideInRoomLocalDataSource(rateDao: CurrencyDao): RoomDataSource {
        return InRoomLocalDataSourceAddRate(rateDao)
    }


    @Singleton
    @Provides
    fun provideRoomRatesRepository(roomDataSource: RoomDataSource): RoomRepository {
        return RoomRepository(roomDataSource)
    }


    @Singleton
    @Provides
    fun provideAddRateUseCase(roomRepository: RoomRepository): AddRateUseCase {
        return AddRateUseCase(roomRepository)
    }

    @Singleton
    @Provides
    fun provideGetRatesRoomUseCase(roomRepository: RoomRepository): GetRatesLocalUseCase {
        return GetRatesLocalUseCase(roomRepository)
    }


}