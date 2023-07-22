package com.ahmedorabi.currencyapplication.core.repo

import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import javax.inject.Inject

class RoomRepository @Inject constructor(private val roomDataSource: RoomDataSource) {

    suspend fun insertRate(rateModel: CurrencyDbModel) = roomDataSource.insertRate(rateModel)
    suspend fun getRates() = roomDataSource.getRates()
}