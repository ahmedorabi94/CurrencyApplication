package com.ahmedorabi.currencyapplication.core.data.api

import com.ahmedorabi.currencyapplication.core.domain.model.RatesResponse
import retrofit2.http.GET

interface ApiService {


    @GET("latest?access_key=a38bfb785a00a8e6cf2310da24dd5d66")
    suspend fun getCurrencyRatesAsync(): RatesResponse


}