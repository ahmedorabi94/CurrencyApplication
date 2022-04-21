package com.ahmedorabi.currencyapplication.core.repo

import com.ahmedorabi.currencyapplication.core.data.api.ResultWrapper
import com.ahmedorabi.currencyapplication.core.domain.model.RatesResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyDataSource {

    suspend fun getRatesResponse(): Flow<ResultWrapper<RatesResponse>>

}