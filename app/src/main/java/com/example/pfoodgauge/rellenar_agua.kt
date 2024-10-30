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

class rellenar_agua : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rellenar_agua)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainra)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sharedPreferences = getSharedPreferences("pAgua", Context.MODE_PRIVATE)
        var cantidadActual = sharedPreferences.getInt("aguag", 0)


        fun actcant(canact: TextView){
            canact.text = "Cantidad actual: $cantidadActual mL"
        }

        fun agagua(cantidad: Int, canact: TextView){
            cantidadActual +=cantidad
            actcant(canact)
        }

        val canact: TextView = findViewById(R.id.tvcantidadAgua)
        actcant(canact)
        val btnpoco: Button = findViewById(R.id.btnAguaPoco)
        val btnmedio: Button = findViewById(R.id.btnAguaMedio)
        val btnalto: Button = findViewById(R.id.btnAguaAlto)



        btnpoco.setOnClickListener {
            agagua(30, canact)
        }
        btnmedio.setOnClickListener {
            agagua(50, canact)
        }
        btnalto.setOnClickListener {
            agagua(100, canact)
        }

        val cambiarPantallaa: TextView = findViewById(R.id.btn_volvera)
        cambiarPantallaa.setOnClickListener { val intent = Intent(this, funcionalidades::class.java)
            intent.putExtra("cantidad_agua_actual",cantidadActual)
            sharedPreferences.edit().putInt("aguag", cantidadActual).apply()
            startActivity(intent)}

    }
}