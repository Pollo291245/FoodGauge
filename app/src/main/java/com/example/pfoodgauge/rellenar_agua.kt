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
import com.example.pfoodgauge.databinding.ActivityRellenarAguaBinding
import com.google.firebase.database.*

class rellenar_agua : AppCompatActivity() {

    private lateinit var binding: ActivityRellenarAguaBinding
    private lateinit var database: DatabaseReference
    private var cantidadActual: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRellenarAguaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainra)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = FirebaseDatabase.getInstance().getReference("agua/medicion")

        val canact: TextView = findViewById(R.id.tvcantidadAgua)
        val btnpoco: Button = findViewById(R.id.btnAguaPoco)
        val btnmedio: Button = findViewById(R.id.btnAguaMedio)
        val btnalto: Button = findViewById(R.id.btnAguaAlto)


        obtenerCantidadAgua(canact)

        btnpoco.setOnClickListener {
            actualizarCantidadAgua(30.0, canact)
        }
        btnmedio.setOnClickListener {
            actualizarCantidadAgua(50.0, canact)
        }
        btnalto.setOnClickListener {
            actualizarCantidadAgua(100.0, canact)
        }



        binding.btnvolvera.setOnClickListener{
            intent= Intent(this,funcionalidades::class.java)
            startActivity(intent)
        }
    }

    private fun obtenerCantidadAgua(canact: TextView) {
        database.child("volumen_litros").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val volumenLitros = snapshot.getValue(Double::class.java)
                if (volumenLitros != null) {
                    cantidadActual = volumenLitros
                    canact.text = "Cantidad actual: $cantidadActual g"
                } else {
                    canact.text = "Error al obtener la cantidad de agua"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@rellenar_agua,
                    "Error al conectar con Firebase: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    private fun actualizarCantidadAgua(cantidad: Double, canact: TextView) {
        cantidadActual += cantidad
        database.child("volumen_litros").setValue(cantidadActual)
            .addOnSuccessListener {
                canact.text = "Cantidad actual: $cantidadActual g"
            }
            .addOnFailureListener {
                Toast.makeText(
                    this@rellenar_agua,
                    "Error al actualizar la cantidad de agua",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}


