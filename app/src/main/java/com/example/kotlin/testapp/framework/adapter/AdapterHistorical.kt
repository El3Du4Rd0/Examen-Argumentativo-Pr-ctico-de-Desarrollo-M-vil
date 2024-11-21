package com.example.kotlin.testapp.framework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.testapp.R
import com.example.kotlin.testapp.data.network.model.HistoricalData

class AdapterHistorical(private var datos: List<HistoricalData> = emptyList()) : RecyclerView.Adapter<AdapterHistorical.HistoricalViewHolder>() {

    private var historicalDataFiltrada = datos

    fun updateData(newData: List<HistoricalData>) {
        datos = newData
        historicalDataFiltrada = newData
        notifyDataSetChanged()
    }

    inner class HistoricalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: HistoricalData) {
            val descripcion: TextView = itemView.findViewById(R.id.titulo)
            val categoria: TextView = itemView.findViewById(R.id.director)
            val fecha: TextView = itemView.findViewById(R.id.presupuesto)

            descripcion.text = item.description // Asigna el valor
            categoria.text = item.category2     // Asigna el valor
            fecha.text = item.date             // Asigna el valor
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalViewHolder  {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historia, parent, false)
        return HistoricalViewHolder (view)
    }

    override fun onBindViewHolder(holder: HistoricalViewHolder, position: Int) {
        holder.bind(historicalDataFiltrada[position])
    }

    override fun getItemCount() = historicalDataFiltrada.size

    fun filtrar(query: String) {
        historicalDataFiltrada = if (query.isEmpty()) {
            datos
        } else {
            datos.filter {
                it.category2?.contains(query, ignoreCase = true) ?:false
            }
        }
        notifyDataSetChanged()
    }

}
