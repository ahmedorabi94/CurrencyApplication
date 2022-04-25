package com.ahmedorabi.currencyapplication.features.rates_list.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedorabi.currencyapplication.TestCoroutineRule
import com.ahmedorabi.currencyapplication.core.data.api.Resource
import com.ahmedorabi.currencyapplication.core.domain.model.RatesResponse
import com.ahmedorabi.currencyapplication.core.domain.usecases.AddRateUseCase
import com.ahmedorabi.currencyapplication.core.domain.usecases.GetRatesUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RatesListViewModelTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiRatesObserver: Observer<Resource<RatesResponse>>

    private lateinit var viewModel: RatesListViewModel


    @Mock
    private lateinit var useCase: GetRatesUseCase

    @Mock
    private lateinit var addRateUseCase: AddRateUseCase

    @Before
    fun setup() {
        //   viewModel = RatesListViewModel(useCase,addRateUseCase)
    }

    @Test
    fun shouldGetRatesListSuccessResponse() {

        val rateResponse = RatesResponse("", "", emptyMap(), true, 1)
        val result1 = Resource.success(rateResponse)


        val flow = flow {
            emit(result1)
        }
        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(useCase)
                .invoke()

            viewModel = RatesListViewModel(useCase, addRateUseCase)

            viewModel.ratesResponse.observeForever(apiRatesObserver)

            Mockito.verify(useCase).invoke()

            Mockito.verify(apiRatesObserver).onChanged(Resource.success(rateResponse))

            assertEquals(viewModel.ratesResponse.value, result1)

            viewModel.ratesResponse.removeObserver(apiRatesObserver)


        }


    }


}