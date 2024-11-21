package com.example.kotlin.testapp.data.repository

import android.util.Log
import com.example.kotlin.testapp.data.network.ServicioApi
import com.example.kotlin.testapp.data.network.model.HistoricalData
import com.example.kotlin.testapp.data.network.model.RespuestaApi
import com.example.kotlin.testapp.domain.HistoricalRepository
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoricalRepositoryImp(private val apiService: ServicioApi) : HistoricalRepository {

    override fun getHistoricalData(callback: (List<HistoricalData>?, String?) -> Unit) {
        val requestBody = RequestBody.create(null, "") // Cuerpo vacío
        val call = apiService.getHistoricalData(requestBody)

        call.enqueue(object : Callback<RespuestaApi> {
            override fun onResponse(call: Call<RespuestaApi>, response: Response<RespuestaApi>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    val result = body?.result
                    if (result?.error == null) {
                        callback(result?.data, null)
                    } else {
                        callback(null, "Error en la respuesta: ${result.error}")
                    }
                } else {
                    callback(null, "Error HTTP: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RespuestaApi>, t: Throwable) {
                callback(null, "Error en la solicitud: ${t.message}")
            }
        })
    }

}