package com.example.pfoodgauge

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pfoodgauge.databinding.ActivityTemperaturaBinding
import com.google.firebase.database.*

class temperatura : AppCompatActivity() {

    private lateinit var binding: ActivityTemperaturaBinding
    private lateinit var database: DatabaseReference
    private lateinit var temperaturaTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTemperaturaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.maint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        temperaturaTextView = findViewById(R.id.tvtemperaturaActual)

        database = FirebaseDatabase.getInstance().getReference("temperatura/medicion")
        obtenerTemperatura()

        binding.btnVolvert.setOnClickListener{
            intent= Intent(this,funcionalidades::class.java)
            startActivity(intent)
        }

    }

    private fun obtenerTemperatura() {
        database.child("temperatura").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val temperatura = snapshot.getValue(Double::class.java)
                if (temperatura != null) {
                    temperaturaTextView.text = "Temperatura: $temperatura°C"
                } else {
                    temperaturaTextView.text = "Error al obtener temperatura"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@temperatura,
                    "Error al conectar con Firebase: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
