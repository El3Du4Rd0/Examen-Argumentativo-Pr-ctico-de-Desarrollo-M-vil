package com.example.kotlin.testapp.framework.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.testapp.R
import com.example.kotlin.testapp.data.network.model.pelicula
import com.example.kotlin.testapp.framework.adapter.adapter_peliculas

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)

        val peliculas = listOf(
            pelicula("saw 1", "no sé","1000"),
            pelicula("it", "no sé","2000"),
            pelicula("actividad paranormal", "no sé","3000"),
            pelicula("destino final", "no sé","4000"),
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = adapter_peliculas(peliculas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

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
    }
}
