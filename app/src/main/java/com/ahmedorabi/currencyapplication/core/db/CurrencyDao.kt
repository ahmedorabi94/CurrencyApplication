package com.ahmedorabi.currencyapplication.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import com.ahmedorabi.currencyapplication.core.domain.model.RateModel
import java.util.*

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRate(currencyDbModel: CurrencyDbModel)

    @Query("Select * from currency")
    fun getAllRates(): List<CurrencyDbModel>


    @Query("Select * from currency where created_at BETWEEN :from AND :to")
    fun getAllRatesTest(from: Date, to: Date): List<CurrencyDbModel>


//    @Query("Select * from currency where created_at >= datetime('now', '-3 day')")
//    fun getAllRatesTest1(from: Date, to: Date): List<CurrencyDbModel>


//    @Query("Select * from currency where CAST((created_at / 1000) AS INTEGER) BETWEEN strftime('%s','now','-3 days') AND strftime('%s','now')  ORDER BY currencyId DESC")
//    fun getAllRatesTest1(from: Date, to: Date): List<CurrencyDbModel>
}