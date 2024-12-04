package com.example.pfoodgauge

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pfoodgauge.databinding.ActivityCambiarContraBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class cambiarContra : AppCompatActivity() {

    private lateinit var binding: ActivityCambiarContraBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCambiarContraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cambiarC)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth= Firebase.auth

        binding.btnVolver.setOnClickListener{
            intent= Intent(this,funcionalidades::class.java)
            startActivity(intent)
        }

        binding.btnCambiarContrasena.setOnClickListener{
        val user = Firebase.auth.currentUser
        val contraActual:String = binding.etContrasenaActual.text.toString()
        val contraNueva:String = binding.etNuevaContrasena.text.toString()
        val contraNueva2:String = binding.etConfirmarNuevaContrasena.text.toString()

            if (contraActual == contraNueva) {
                Toast.makeText(this, "La nueva contraseña no puede ser igual a la actual", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (contraNueva != contraNueva2) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (contraNueva.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val credential = EmailAuthProvider.getCredential(user!!.email.toString(), contraActual)
            user.reauthenticate(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cambiando la contraseña", Toast.LENGTH_SHORT).show()




                    user.updatePassword(contraNueva).addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            Toast.makeText(this, "Contraseña cambiada, inicie sesión nuevamente", Toast.LENGTH_SHORT).show()
                            signOut()
                            finish()
                        } else {
                            Toast.makeText(this, "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
        private fun signOut(){
            Firebase.auth.signOut()
            Toast.makeText(this,"sesion cerrada",Toast.LENGTH_SHORT).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
    }
}