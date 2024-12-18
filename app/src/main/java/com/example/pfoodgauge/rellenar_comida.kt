package com.example.pfoodgauge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pfoodgauge.databinding.ActivityRellenarComidaBinding
import com.google.firebase.database.*

class rellenar_comida : AppCompatActivity() {

    private lateinit var binding: ActivityRellenarComidaBinding
    private lateinit var database: DatabaseReference
    private var cantidadActual: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRellenarComidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainrc)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = FirebaseDatabase.getInstance().getReference("comida/medicion")


        val canact: TextView = findViewById(R.id.tvcantactual)
        val btnpoco: Button = findViewById(R.id.btnPoco)
        val btnmedio: Button = findViewById(R.id.btnMedio)
        val btnalto: Button = findViewById(R.id.btnAlto)

        obtenerCantidadComida(canact)


        btnpoco.setOnClickListener {
            actualizarCantidadComida(30.0, canact)
        }
        btnmedio.setOnClickListener {
            actualizarCantidadComida(50.0, canact)
        }
        btnalto.setOnClickListener {
            actualizarCantidadComida(100.0, canact)
        }

        binding.btnvolverc.setOnClickListener{
            intent= Intent(this,funcionalidades::class.java)
            startActivity(intent)
        }
    }

    private fun obtenerCantidadComida(canact: TextView) {
        database.child("peso_gramos").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val cantidad = snapshot.getValue(Double::class.java)
                if (cantidad != null) {
                    cantidadActual = cantidad
                    canact.text = "Cantidad actual: $cantidadActual g"
                } else {
                    canact.text = "Error al obtener la cantidad de comida"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@rellenar_comida,
                    "Error al conectar con Firebase: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun actualizarCantidadComida(cantidad: Double, canact: TextView) {
        cantidadActual += cantidad
        database.child("peso_gramos").setValue(cantidadActual)
            .addOnSuccessListener {
                canact.text = "Cantidad actual: $cantidadActual g"
            }
            .addOnFailureListener {
                Toast.makeText(
                    this@rellenar_comida,
                    "Error al actualizar la cantidad de comida",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
