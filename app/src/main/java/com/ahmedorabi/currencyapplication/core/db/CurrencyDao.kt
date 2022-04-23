package com.ahmedorabi.currencyapplication.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import com.ahmedorabi.currencyapplication.core.domain.model.RateModel

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRate(currencyDbModel: CurrencyDbModel)

    @Query("Select * from currency")
    fun getAllRates(): List<CurrencyDbModel>


//    @Query("Select * from currency where created_at >= DATEADD(day,-7, GETDATE())")
//    fun getAllRatesTest(): List<CurrencyDbModel>
}