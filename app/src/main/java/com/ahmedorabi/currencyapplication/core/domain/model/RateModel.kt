package com.ahmedorabi.currencyapplication.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RateModel(
    val name: String,
    val rateValue: Double
) : Parcelable {

    override fun toString(): String {
        return name
    }

     fun getPopularCurrency(): String {
        return name + "\n"
    }


}