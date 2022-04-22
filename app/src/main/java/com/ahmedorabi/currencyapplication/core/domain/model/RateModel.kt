package com.ahmedorabi.currencyapplication.core.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "currency")
@Parcelize
data class RateModel (
    @PrimaryKey(autoGenerate = true)
    var currencyId: Int = 0,
    val name : String ,
    val rateValue : Double ) : Parcelable{

    override fun toString(): String {
        return name
    }
}