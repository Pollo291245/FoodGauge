package com.example.pfoodgauge

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class temperatura : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temperatura)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.maint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cambiarPantallaa: TextView = findViewById(R.id.btn_volvert)
        cambiarPantallaa.setOnClickListener { val intent = Intent(this, funcionalidades::class.java)
            startActivity(intent)}
    }
}