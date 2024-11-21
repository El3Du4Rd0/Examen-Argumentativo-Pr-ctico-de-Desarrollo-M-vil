package com.example.kotlin.testapp.framework.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.testapp.R
import com.example.kotlin.testapp.framework.adapter.AdapterHistorical
import com.example.kotlin.testapp.framework.viewmodel.HistoricalViewModel
import com.example.kotlin.testapp.framework.viewmodel.HistoricalViewModelFactory

class RecyclerViewActivity : AppCompatActivity() {

    private val viewModel: HistoricalViewModel by viewModels { HistoricalViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = AdapterHistorical()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.historicalData.observe(this) { data ->
            if (!data.isNullOrEmpty()) {
                Log.d("RecyclerViewActivity", "Actualizando adaptador con datos: $data")
                adapter.updateData(data)
            } else {
                Log.w("RecyclerViewActivity", "No hay datos para mostrar.")
            }
        }

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filtrar(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewModel.error.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.fetchHistoricalData()
    }
}
