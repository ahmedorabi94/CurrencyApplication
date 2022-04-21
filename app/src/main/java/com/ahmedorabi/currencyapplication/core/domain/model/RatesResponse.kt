package com.ahmedorabi.currencyapplication.core.domain.model

data class RatesResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)