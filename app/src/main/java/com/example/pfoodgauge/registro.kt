package com.example.pfoodgauge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainr)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferencesn = getSharedPreferences("pnombre", Context.MODE_PRIVATE)
        val sharedPreferencesu = getSharedPreferences("pusuario", Context.MODE_PRIVATE)
        val sharedPreferencesc = getSharedPreferences("pcontra", Context.MODE_PRIVATE)


        val nombrei =findViewById<EditText>(R.id.etnombre)
        val usuarioi = findViewById<EditText>(R.id.etusuario)
        val contrasenai = findViewById<EditText>(R.id.etcontrasena)



        val cambiarPantallartl: TextView = findViewById(R.id.tvbiniciarSesion)
        cambiarPantallartl.setOnClickListener { val intent = Intent(this, login::class.java)
            startActivity(intent)}

        val cambiarPantallar: Button = findViewById(R.id.btnregistro)
        cambiarPantallar.setOnClickListener {
            val nombre = nombrei.text.toString()
            val usuario = usuarioi.text.toString()
            val contrasena = contrasenai.text.toString()

            sharedPreferencesn.edit().putString("nombreg",nombre).apply()
            sharedPreferencesu.edit().putString("usuariog",usuario).apply()
            sharedPreferencesc.edit().putString("contrag",contrasena).apply()

            val intent = Intent(this, login::class.java)
            startActivity(intent)}
    }
}