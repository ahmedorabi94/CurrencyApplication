package com.ahmedorabi.currencyapplication.core.domain.model

data class RatesResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean,
    val timestamp: Int
)