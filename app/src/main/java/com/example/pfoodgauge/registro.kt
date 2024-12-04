package com.example.pfoodgauge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pfoodgauge.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainr)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth= Firebase.auth

        binding.tvbiniciarSesion.setOnClickListener {
            val intent= Intent(this, login::class.java)
            startActivity(intent)
        }

        binding.btnregistro.setOnClickListener{
            val nombre= binding.etnombre.text.toString()
            val email= binding.etemail.text.toString()
            val contra= binding.etcontrasena.text.toString()
            val confcontra= binding.etconfcontrasena.text.toString()

            if (nombre.isEmpty()){
                binding.etnombre.error= "Por favor ingrese un nombre"
                return@setOnClickListener
            }
            if (email.isEmpty()){
                binding.etemail.error= "Por favor ingrese un correo"
                return@setOnClickListener
            }

            if (contra.isEmpty()){
                binding.etcontrasena.error= "Por favor ingrese una contraseña"
                return@setOnClickListener
            }

            if (confcontra.isEmpty()){
                binding.etconfcontrasena.error= "Por favor ingrese la contraseña nuevamente"
                return@setOnClickListener
            }

            if (contra.length<6){
                binding.etcontrasena.error= "La contraseña debe tener al menos 6 caracteres"
                return@setOnClickListener
            }
            if (nombre.length<3){
                binding.etnombre.error= "El nombre debe tener al menos 3 caracteres"
                return@setOnClickListener
            }

            if (contra != confcontra){
                binding.etconfcontrasena.error= "Las contraseñas no coinciden"
                return@setOnClickListener
            } else {
                signUp(email,contra)
            }

        }

    }
    private fun signUp(email: String, contra: String){
        auth.createUserWithEmailAndPassword(email,contra)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, login::class.java))
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            }
    }
}