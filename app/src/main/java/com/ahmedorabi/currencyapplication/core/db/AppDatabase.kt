package com.ahmedorabi.currencyapplication.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel

@Database(entities = [CurrencyDbModel::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rateDao(): CurrencyDao
}