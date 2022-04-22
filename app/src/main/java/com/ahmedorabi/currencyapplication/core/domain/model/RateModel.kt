package com.ahmedorabi.currencyapplication.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RateModel (val name : String , val value : Double , val resId : Int =0 ) : Parcelable{

    override fun toString(): String {
        return name
    }
}