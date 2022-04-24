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
import java.util.*
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

    var fromPosition = 0
    var toPosition = 0
    var fromValue = ""
    var toValue = ""

//    var isFromChanged = false
//    var isToChanged = false

    val popularList = ArrayList<RateModel>()
//    val to1 = RateModel(name = "AED", rateValue = 4.0)
//    val to2 = RateModel(name = "EGP", rateValue = 20.0)
//    val to3 = RateModel(name = "EUR", rateValue = 1.0)
//    val to4 = RateModel(name = "USD", rateValue = 2.0)
    var isGoToNextScreen = false


    init {
//        popularList.add(to1)
//        popularList.add(to2)
//        popularList.add(to3)
//        popularList.add(to4)

        getRatesResponseFlow()
    }


    fun addRate() {
        if (fromValue.isNotEmpty() && toValue.isNotEmpty()){
            val currencyDbModel = CurrencyDbModel(
                fromName = from.name,
                ToName = to.name,
                fromValue = fromValue.toDouble(),
                ToValue = toValue.toDouble(),
                createdAt = Date()
            )
            viewModelScope.launch {
                addRateUseCase.invoke(currencyDbModel)
            }
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

    fun getPopularList(map: Map<String, Double>) {
        map.forEach {
            if (it.key == "CAD" || it.key == "EUR" || it.key == "JPY"
                || it.key == "USD" || it.key == "GBP" || it.key == "AUD"
                || it.key == "CHF" || it.key == "CNY" || it.key == "HKD" || it.key == "NZD"
            ) {
                popularList.add(RateModel(name = it.key, rateValue = it.value))

            }
        }
    }

}