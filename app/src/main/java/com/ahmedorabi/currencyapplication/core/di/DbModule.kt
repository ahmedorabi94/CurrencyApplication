package com.ahmedorabi.currencyapplication.core.di

import android.app.Application
import androidx.room.Room
import com.ahmedorabi.currencyapplication.core.db.AppDatabase
import com.ahmedorabi.currencyapplication.core.db.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {

        return Room.databaseBuilder(app, AppDatabase::class.java, "currencies.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideRateDao(db: AppDatabase): CurrencyDao {
        return db.rateDao()
    }
}