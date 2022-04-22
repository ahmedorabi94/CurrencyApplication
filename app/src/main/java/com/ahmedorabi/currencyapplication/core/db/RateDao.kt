package com.ahmedorabi.currencyapplication.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedorabi.currencyapplication.core.domain.model.RateModel

@Dao
interface RateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRate(rateModel: RateModel)

    @Query("Select * from currency")
    fun getAllRates(): List<RateModel>

}