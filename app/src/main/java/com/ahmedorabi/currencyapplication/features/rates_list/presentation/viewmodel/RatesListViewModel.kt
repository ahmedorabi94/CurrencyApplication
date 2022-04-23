package com.ahmedorabi.currencyapplication.features.rates_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedorabi.currencyapplication.core.data.api.Resource
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import com.ahmedorabi.currencyapplication.core.domain.model.RateModel
import com.ahmedorabi.currencyapplication.core.domain.model.RatesResponse
import com.ahmedorabi.currencyapplication.core.domain.usecases.AddRateUseCase
import com.ahmedorabi.currencyapplication.core.domain.usecases.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RatesListViewModel @Inject constructor(
    private val useCase: GetRatesUseCase,
    private val addRateUseCase: AddRateUseCase
) :
    ViewModel() {

    private val _ratesResponse = MutableLiveData<Resource<RatesResponse>>()
    val ratesResponse: LiveData<Resource<RatesResponse>>
        get() = _ratesResponse

    var from: RateModel = RateModel(name = "", rateValue = 0.0)
    var to: RateModel = RateModel(name = "", rateValue = 0.0)


    var isSwap = false
    var fromPosition = 0
    var toPosition = 0
    var fromValue = ""
    var toValue = ""


    init {
        // getRatesResponseFlow()
    }


    fun addRate() {
        val currencyDbModel = CurrencyDbModel(
            fromName = from.name,
            ToName = to.name,
            fromValue = fromValue.toDouble(),
            ToValue = toValue.toDouble(),
            createdAt = System.currentTimeMillis()
        )
        viewModelScope.launch {
            addRateUseCase.invoke(currencyDbModel)
        }
    }

    private fun getRatesResponseFlow() {
        viewModelScope.launch {
            useCase.invoke()
                .collect { response ->
                    _ratesResponse.value = response
                }
        }

    }


    fun getExchangeRate(amount: Double): Double {
        return (amount * to.rateValue) / from.rateValue
    }

}