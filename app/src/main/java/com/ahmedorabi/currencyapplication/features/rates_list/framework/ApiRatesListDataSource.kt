package com.ahmedorabi.currencyapplication.features.rates_list.framework

import com.ahmedorabi.currencyapplication.core.data.api.ApiService
import com.ahmedorabi.currencyapplication.core.data.api.Resource
import com.ahmedorabi.currencyapplication.core.data.api.ResultWrapper
import com.ahmedorabi.currencyapplication.core.data.api.safeApiCall
import com.ahmedorabi.currencyapplication.core.domain.model.RatesResponse
import com.ahmedorabi.currencyapplication.core.repo.CurrencyDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ApiRatesListDataSource @Inject constructor(private val apiService: ApiService) :
    CurrencyDataSource {


    override suspend fun  getRatesResponse(): Flow<Resource<RatesResponse>> {

        return flow {

            emit(Resource.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getCurrencyRatesAsync()
            }

            when (response) {
                is ResultWrapper.Success -> {
                    emit(Resource.success(response.value))
                }
                is ResultWrapper.Error -> {
                    emit(Resource.error(response.error?.message ?: "Unknown Error"))

                }
                is ResultWrapper.NetworkError -> {
                    emit(Resource.error("NetworkError"))

                }
                ResultWrapper.NoContentError -> {
                    emit(Resource.error("NoContentError"))

                }
            }

        }.flowOn(Dispatchers.IO)
    }


}