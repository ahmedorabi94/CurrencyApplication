package com.ahmedorabi.currencyapplication.features.rates_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedorabi.currencyapplication.core.data.api.Resource
import com.ahmedorabi.currencyapplication.core.domain.model.RatesResponse
import com.ahmedorabi.currencyapplication.core.domain.usecases.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RatesListViewModel @Inject constructor(
    private val useCase: GetRatesUseCase,
) :
    ViewModel() {

    private val _ratesResponse = MutableLiveData<Resource<RatesResponse>>()
    val ratesResponse: LiveData<Resource<RatesResponse>>
        get() = _ratesResponse


    init {
       getRatesResponseFlow()
    }

    private fun getRatesResponseFlow() {
        viewModelScope.launch {
            useCase.invoke()
                .collect { response ->

                    _ratesResponse.value = response

                }
        }

    }

}