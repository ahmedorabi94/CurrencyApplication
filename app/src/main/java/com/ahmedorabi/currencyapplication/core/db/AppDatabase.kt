package com.ahmedorabi.currencyapplication.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedorabi.currencyapplication.core.domain.model.RateModel

@Database(entities = [RateModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rateDao(): RateDao
}