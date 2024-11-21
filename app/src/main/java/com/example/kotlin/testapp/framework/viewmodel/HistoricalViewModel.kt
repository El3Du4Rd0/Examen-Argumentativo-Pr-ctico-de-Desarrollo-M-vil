package com.example.kotlin.testapp.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.testapp.data.network.model.HistoricalData
import com.example.kotlin.testapp.domain.HistoricalRepository

class HistoricalViewModel(private val repository: HistoricalRepository) : ViewModel() {
    private val _historicalData = MutableLiveData<List<HistoricalData>?>()
    val historicalData: MutableLiveData<List<HistoricalData>?> get() = _historicalData

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchHistoricalData() {
        repository.getHistoricalData { data, error ->
            if (data != null) {
                _historicalData.postValue(data)
            } else {
                _error.postValue(error)
            }
        }
    }
}