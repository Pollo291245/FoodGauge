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
import com.example.pfoodgauge.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainl)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth= Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.etemail.text.toString()
            val contra= binding.etcontrasenal.text.toString()

            if (email.isEmpty()){
                binding.etemail.error= "Por favor ingrese su correo"
                return@setOnClickListener
            }
            if (contra.isEmpty()){
                binding.etcontrasenal.error = "Por favor ingrese su contraseña"
            }

            signIn(email,contra)
        }

        binding.tvbregistrarse.setOnClickListener {
            val intent= Intent(this, registro::class.java)
            startActivity(intent)
        }

    }
    private fun signIn(email: String, contra: String){
        auth.signInWithEmailAndPassword(email, contra)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show()

                    try {
                        val intent = Intent(this, funcionalidades::class.java)
                        startActivity(intent)
                    }catch (e: Exception){
                        Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this, "error al iniciar sesion", Toast.LENGTH_SHORT).show()
                }
            }
    }
}