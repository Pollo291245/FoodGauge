package com.example.pfoodgauge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class funcionalidades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_funcionalidades)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainf)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val cambiarPantallac: TextView = findViewById(R.id.btn_rellenar_comida)
        cambiarPantallac.setOnClickListener { val intent = Intent(this, rellenar_comida::class.java)
            startActivity(intent)}

        val cambiarPantallad: TextView = findViewById(R.id.btnDetalles)
        cambiarPantallad.setOnClickListener { val intent = Intent(this, temperatura::class.java)
            startActivity(intent)}

        val cambiarPantallaa: TextView = findViewById(R.id.btn_rellenar_Agua)
        cambiarPantallaa.setOnClickListener { val intent = Intent(this, rellenar_agua::class.java)
            startActivity(intent)}

        val sharedPreferencesAgua = getSharedPreferences("pAgua", Context.MODE_PRIVATE)
        val cantidadAgua = sharedPreferencesAgua.getInt("aguag", 0)

        val sharedPreferencesComida = getSharedPreferences("pComida", Context.MODE_PRIVATE)
        val cantidadComida = sharedPreferencesComida.getInt("comidag", 0)

        val cantidadAguatv: TextView = findViewById(R.id.tvporcentajeAgua)
        cantidadAguatv.text = "$cantidadAgua mL"

        val cantidadComidatv: TextView= findViewById(R.id.tvporcentajeAlimento)
        cantidadComidatv.text= "$cantidadComida g"

        val cambiarPantallam: TextView = findViewById(R.id.btn_salir)
        cambiarPantallam.setOnClickListener { val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)}

    }
}