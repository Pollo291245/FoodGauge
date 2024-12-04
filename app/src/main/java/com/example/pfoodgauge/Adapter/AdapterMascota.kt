package com.example.pfoodgauge.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pfoodgauge.Models.mascota
import com.example.pfoodgauge.R

class AdapterMascota(private var mascotas: ArrayList <mascota>):
        RecyclerView.Adapter<AdapterMascota.ViewHolder>()
{
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tvNombreMascota)
        val especie: TextView = itemView.findViewById(R.id.tvEspecieMascota)
        val edad: TextView = itemView.findViewById(R.id.tvEdadMascota)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMascota.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mascota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mascota = mascotas[position]
        holder.nombre.text = mascota.nombre
        holder.especie.text = mascota.especie
        holder.edad.text = mascota.edad.toString()

    }
    override fun getItemCount(): Int {
        return mascotas.size
    }

    }