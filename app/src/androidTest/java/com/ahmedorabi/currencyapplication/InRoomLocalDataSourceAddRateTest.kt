package com.ahmedorabi.currencyapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.ahmedorabi.currencyapplication.core.db.AppDatabase
import com.ahmedorabi.currencyapplication.core.db.CurrencyDao
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class InRoomLocalDataSourceAddRateTest {


    private lateinit var rateDao: CurrencyDao
    private lateinit var db: AppDatabase


    @Before
    fun setup() {


        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        rateDao = db.rateDao()


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertRate() {
        val currencyDbModel = CurrencyDbModel(
            1, "EGP", 1.2, "EUR", 1.55, Date()
        )


        runBlocking {
            rateDao.insertRate(currencyDbModel)

            val rates = rateDao.getAllRates()

            assertEquals(rates[0].fromName, "EGP")
        }

    }


}