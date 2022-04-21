package com.ahmedorabi.currencyapplication.core.repo

import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val dataSource: CurrencyDataSource) {

    suspend fun getRatesResponse() = dataSource.getRatesResponse()

}