package com.ahmedorabi.currencyapplication.features.currency_details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import com.ahmedorabi.currencyapplication.core.domain.usecases.GetRatesLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailViewModel @Inject constructor(
    private val getRatesLocalUseCase: GetRatesLocalUseCase
) :
    ViewModel() {


    private val _ratesResponse = MutableLiveData<List<CurrencyDbModel>>()
    val ratesResponse: LiveData<List<CurrencyDbModel>>
        get() = _ratesResponse


    init {
       getRates()
    }

     private fun getRates(){
        viewModelScope.launch {
            getRatesLocalUseCase.invoke().collect { rates ->
                _ratesResponse.value = rates
            }

        }
    }
}