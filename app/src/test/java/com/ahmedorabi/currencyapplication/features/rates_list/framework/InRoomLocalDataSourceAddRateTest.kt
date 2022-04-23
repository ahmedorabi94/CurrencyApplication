package com.ahmedorabi.currencyapplication.features.rates_list.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedorabi.currencyapplication.TestCoroutineRule
import com.ahmedorabi.currencyapplication.core.data.api.ApiService
import com.ahmedorabi.currencyapplication.core.data.api.Resource
import com.ahmedorabi.currencyapplication.core.db.CurrencyDao
import com.ahmedorabi.currencyapplication.core.domain.model.RatesResponse
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class InRoomLocalDataSourceAddRateTest {



    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    lateinit var  rateDao: CurrencyDao


    private lateinit var apiRatesListDataSource: InRoomLocalDataSourceAddRate


    @Before
    fun setup() {

        apiRatesListDataSource = InRoomLocalDataSourceAddRate(rateDao)

    }

//    @Test
//    fun shouldGetRatesSuccessResponse() {
//
//
//        val rateResponse = RatesResponse("", "", emptyMap(), true, 1)
//        val result1 = Resource.success(rateResponse)
//
//        runBlocking {
//
//            Mockito.doReturn(rateResponse)
//                .`when`(rateResponse)
//                .getCurrencyRatesAsync()
//
//            val response = apiRatesListDataSource.getRatesResponse().drop(1).first()
//
//            Assert.assertEquals(response, result1)
//
//        }
//    }
//
//
//    @Test
//    fun shouldGetListRatesFailureResponse() {
//
//        val result1 = Resource.error<RatesResponse>("NetworkError")
//
//
//        runBlocking {
//
//            BDDMockito.given(apiService.getCurrencyRatesAsync()).willAnswer {
//                throw IOException("Ooops")
//            }
//
//            val response = apiRatesListDataSource.getRatesResponse().drop(1).first()
//
//            Assert.assertEquals(response, result1)
//
//
//        }
//    }


}