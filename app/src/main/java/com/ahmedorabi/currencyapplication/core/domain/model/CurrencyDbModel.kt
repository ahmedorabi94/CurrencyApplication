package com.ahmedorabi.currencyapplication.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyDbModel (
    @PrimaryKey(autoGenerate = true)
    var currencyId: Int = 0,
    val fromName : String ,
    val fromValue : Double ,
    val ToName : String ,
    val ToValue : Double )