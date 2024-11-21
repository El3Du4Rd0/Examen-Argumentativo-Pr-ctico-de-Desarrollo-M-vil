package com.example.kotlin.testapp.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.testapp.data.repository.HistoricalRepositoryImp
import com.example.kotlin.testapp.data.network.RetrofitClient
import com.example.kotlin.testapp.domain.HistoricalRepository

class HistoricalViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoricalViewModel::class.java)) {
            val apiService = RetrofitClient.servicioApi
            val repository: HistoricalRepository = HistoricalRepositoryImp(apiService)
            return HistoricalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}