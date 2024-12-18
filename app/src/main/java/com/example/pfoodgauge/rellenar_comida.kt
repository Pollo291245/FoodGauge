package com.example.pfoodgauge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class rellenar_comida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rellenar_comida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainrc)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("pComida", Context.MODE_PRIVATE)
        var cantidadActual: Int = sharedPreferences.getInt("comidag", 0)




        fun actcant(canact: TextView){
            canact.text = "Cantidad actual: $cantidadActual g"
        }

        fun agcomida (cantidad: Int, canact: TextView){
            cantidadActual+=cantidad
            actcant(canact)
        }

        val canact: TextView = findViewById(R.id.tvcantactual)
        actcant(canact)
        val btnpoco: Button= findViewById(R.id.btnPoco)
        val btnmedio: Button= findViewById(R.id.btnMedio)
        val btnalto: Button= findViewById(R.id.btnAlto)



        btnpoco.setOnClickListener {
            agcomida(30, canact)
        }
        btnmedio.setOnClickListener {
            agcomida(50, canact)
        }
        btnalto.setOnClickListener {
            agcomida(100, canact)
        }

        val cambiarPantallaa: TextView = findViewById(R.id.btn_volverc)
        cambiarPantallaa.setOnClickListener { val intent = Intent(this, funcionalidades::class.java)
            sharedPreferences.edit().putInt("comidag", cantidadActual).apply()
            startActivity(intent)}



    }
}