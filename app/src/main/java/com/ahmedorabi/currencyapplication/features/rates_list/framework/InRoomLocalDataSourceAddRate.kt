package com.ahmedorabi.currencyapplication.features.rates_list.framework

import com.ahmedorabi.currencyapplication.core.data.api.ApiService
import com.ahmedorabi.currencyapplication.core.db.RateDao
import com.ahmedorabi.currencyapplication.core.domain.model.RateModel
import com.ahmedorabi.currencyapplication.core.repo.CurrencyDataSource
import com.ahmedorabi.currencyapplication.core.repo.RoomDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InRoomLocalDataSourceAddRate @Inject constructor(private val rateDao: RateDao) :
    RoomDataSource {


    override suspend fun insertRate(rateModel: RateModel) {

    }

    override suspend fun getRates(): Flow<List<RateModel>> {
        TODO("Not yet implemented")
    }
}