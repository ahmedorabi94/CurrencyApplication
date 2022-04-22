package com.ahmedorabi.currencyapplication.core.repo

import com.ahmedorabi.currencyapplication.core.domain.model.RateModel
import javax.inject.Inject

class RoomRepository @Inject constructor(private val roomDataSource: RoomDataSource) {

    suspend fun insertRate(rateModel: RateModel) = roomDataSource.insertRate(rateModel)
    suspend fun getRates() = roomDataSource.getRates()
}