package com.example.kotlin.testapp.data.network.model

data class RespuestaApi(
    val result: ResultData?
)

data class ResultData(
    val code: Int?,
    val count: Int?,
    val page: Int?,
    val data: List<HistoricalData>,
    val error: String?
)

data class HistoricalData(
    val date: String,
    val description: String,
    val lang: String,
    val category1: String,
    val category2: String
)
