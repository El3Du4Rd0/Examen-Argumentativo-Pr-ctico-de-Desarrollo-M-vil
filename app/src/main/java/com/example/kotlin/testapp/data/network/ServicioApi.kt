package com.example.kotlin.testapp.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.Call
import com.example.kotlin.testapp.data.network.model.RespuestaApi
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicioApi {

    @POST("parse/functions/hello?page=1")
    @Headers(
        "X-Parse-Application-Id: APP_ID",
        "X-Parse-REST-API-Key: MASTER_KEY",
        "Content-Type: application/json"
    )
    fun getHistoricalData(@Body requestBody: RequestBody): Call<RespuestaApi>

}