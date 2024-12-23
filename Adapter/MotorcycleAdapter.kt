package com.example.testridenow.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testridenow.API.Motorcycle
import com.example.testridenow.API.User
import com.example.testridenow.R
import retrofit2.*

class MotorcycleAdapter(private var motorcycles: List<Motorcycle>) :
    RecyclerView.Adapter<MotorcycleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.motorcycle_name)
        val model: TextView = view.findViewById(R.id.motorcycle_model)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_motorcycle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val motorcycle = motorcycles[position]
        holder.name.text = motorcycle.name
        holder.model.text = motorcycle.model
    }

    override fun getItemCount(): Int = motorcycles.size

    fun updateData(newMotorcycles: List<Motorcycle>) {
        motorcycles = newMotorcycles
        notifyDataSetChanged()
    }
}
