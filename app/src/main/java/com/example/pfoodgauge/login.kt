package com.example.pfoodgauge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainl)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferencesn = getSharedPreferences("pnombre", Context.MODE_PRIVATE)
        val pnombre = sharedPreferencesn.getString("nombreg", "")

        val sharedPreferencesu = getSharedPreferences("pusuario", Context.MODE_PRIVATE)
        val pusuario = sharedPreferencesu.getString("usuariog", "")

        val sharedPreferencesc = getSharedPreferences("pcontra", Context.MODE_PRIVATE)
        val pcontra = sharedPreferencesc.getString("contrag", "")

        val login: Button = findViewById(R.id.btn_login)
        login.setOnClickListener {
            val usuario = findViewById<EditText>(R.id.etusuariol).text.toString()
            val contrasena= findViewById<EditText>(R.id.etcontrasenal).text.toString()

            if (usuario == pusuario && contrasena == pcontra){
                if (usuario.isNotEmpty() && contrasena.isNotEmpty()){
                    val intent = Intent(this, funcionalidades::class.java)
                    Toast.makeText(this, "Bienvenido $pnombre", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
            }}
            else{
                Toast.makeText(this, "usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show()
            }



            val cambiarPantallar: TextView = findViewById(R.id.tvbregistrarse)
            cambiarPantallar.setOnClickListener { val intent = Intent(this, registro::class.java)
                startActivity(intent)

            }


        }

    }
}