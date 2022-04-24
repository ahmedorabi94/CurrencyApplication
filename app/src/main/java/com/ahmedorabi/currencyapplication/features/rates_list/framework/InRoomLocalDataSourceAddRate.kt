package com.ahmedorabi.currencyapplication.features.rates_list.framework

import com.ahmedorabi.currencyapplication.core.db.CurrencyDao
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import com.ahmedorabi.currencyapplication.core.repo.RoomDataSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class InRoomLocalDataSourceAddRate @Inject constructor(private val rateDao: CurrencyDao) :
    RoomDataSource {


    override suspend fun insertRate(rateModel: CurrencyDbModel) {
        rateDao.insertRate(rateModel)
    }

    override suspend fun getRates(): Flow<List<CurrencyDbModel>> {
        val currentDate = Date(System.currentTimeMillis())
        val date = Calendar.getInstance()
        date.add(Calendar.DATE, -3)
        val dateFromThreeDats = date.time

        return flow {
            emit(rateDao.getAllRatesTest(currentDate, dateFromThreeDats))
        }.flowOn(IO)
    }
}