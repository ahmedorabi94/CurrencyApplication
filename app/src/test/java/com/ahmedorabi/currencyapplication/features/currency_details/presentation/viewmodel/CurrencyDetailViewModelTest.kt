package com.ahmedorabi.currencyapplication.features.currency_details.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedorabi.currencyapplication.TestCoroutineRule
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import com.ahmedorabi.currencyapplication.core.domain.usecases.GetRatesLocalUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencyDetailViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiRatesObserver: Observer<List<CurrencyDbModel>>

    private lateinit var viewModel: CurrencyDetailViewModel

    @Mock
    private lateinit var useCase: GetRatesLocalUseCase


    @Before
    fun setup() {
        viewModel = CurrencyDetailViewModel(useCase)
    }


    @Test
    fun shouldGetRatesListSuccessResponse() {
        val CurrencyDbModel = CurrencyDbModel(
            createdAt = 0,
            currencyId = 1,
            fromName = "",
            fromValue = 0.0,
            ToName = "",
            ToValue = 0.0
        )
        val list = ArrayList<CurrencyDbModel>()
        list.add(CurrencyDbModel)

        val flow = flow {
            emit(list)
        }
        testCoroutineRule.runBlockingTest {


            Mockito.doReturn(flow)
                .`when`(useCase)
                .invoke()

            viewModel.ratesResponse.observeForever(apiRatesObserver)
            viewModel.getRates()

            verify(apiRatesObserver).onChanged(list)

            viewModel.ratesResponse.removeObserver(apiRatesObserver)

            // assertEquals(viewModel.ratesResponse.value, list)
        }


    }


}