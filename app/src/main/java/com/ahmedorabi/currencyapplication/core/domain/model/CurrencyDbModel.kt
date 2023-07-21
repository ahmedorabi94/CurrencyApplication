package com.ahmedorabi.currencyapplication.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "currency" ,  indices = [Index(value = ["fromName","fromValue","ToName","ToValue"], unique = true)])
data class CurrencyDbModel(
    @PrimaryKey(autoGenerate = true)
    var currencyId: Int = 0,
    val fromName: String,
    val fromValue: Double,
    val ToName: String,
    val ToValue: Double,
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
)