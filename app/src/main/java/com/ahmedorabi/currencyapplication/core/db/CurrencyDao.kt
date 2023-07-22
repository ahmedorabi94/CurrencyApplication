package com.ahmedorabi.currencyapplication.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import java.util.*

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRate(currencyDbModel: CurrencyDbModel)

    @Query("Select * from currency")
    fun getAllRates(): List<CurrencyDbModel>


    @Query("Select * from currency where created_at BETWEEN :to AND :currentDate")
    fun getAllRates(currentDate: Date, to: Date): List<CurrencyDbModel>

}