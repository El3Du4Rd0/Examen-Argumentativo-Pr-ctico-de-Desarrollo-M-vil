package com.example.kotlin.testapp.framework.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.testapp.R
import com.example.kotlin.testapp.data.network.model.pelicula

class adapter_peliculas(private val peliculas: List<pelicula>) : RecyclerView.Adapter<adapter_peliculas.PeliculaViewHolder>() {

    private var peliculasFiltradas = peliculas.toMutableList()

    class PeliculaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.titulo)
        val director: TextView = view.findViewById(R.id.director)
        val presupuesto: TextView = view.findViewById(R.id.presupuesto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_peliculas, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = peliculasFiltradas[position]
        holder.nombre.text = pelicula.titulo
        holder.director.text = pelicula.director
        holder.presupuesto.text = pelicula.presupuesto
    }

    override fun getItemCount() = peliculasFiltradas.size

    fun filtrar(query: String) {
        Log.d("Adapter", "Filtrando por: $query")
        peliculasFiltradas.clear()
        if (query.isEmpty()) {
            peliculasFiltradas.addAll(peliculas)
        } else {
            peliculasFiltradas.addAll(peliculas.filter {
                it.titulo.contains(query, ignoreCase = true)
            })
        }
        notifyDataSetChanged()
    }
}
