package com.ahmedorabi.currencyapplication.core.repo

import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import kotlinx.coroutines.flow.Flow

interface RoomDataSource {

   suspend fun insertRate(rateModel: CurrencyDbModel)
    suspend fun getRates() : Flow<List<CurrencyDbModel>>
}