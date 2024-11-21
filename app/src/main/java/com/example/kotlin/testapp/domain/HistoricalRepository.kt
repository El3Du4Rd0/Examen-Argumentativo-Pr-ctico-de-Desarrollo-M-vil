package com.example.kotlin.testapp.domain

import com.example.kotlin.testapp.data.network.model.HistoricalData

interface HistoricalRepository {
    fun getHistoricalData(callback: (List<HistoricalData>?, String?) -> Unit)
}