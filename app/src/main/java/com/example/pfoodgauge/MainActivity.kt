package com.example.pfoodgauge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cambiarPantallal: Button = findViewById(R.id.btnLogin)
        cambiarPantallal.setOnClickListener { val intent = Intent(this, login::class.java)
        startActivity(intent)

        }

        val cambiarPantallar: Button = findViewById(R.id.btnRegistrar)
        cambiarPantallar.setOnClickListener { val intent = Intent(this, registro::class.java)
            startActivity(intent)

        }
    }
}