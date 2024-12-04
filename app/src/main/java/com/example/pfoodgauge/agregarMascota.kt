package com.example.pfoodgauge

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pfoodgauge.Models.mascota
import com.example.pfoodgauge.databinding.ActivityAgregarMascotaBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class agregarMascota : AppCompatActivity() {

    lateinit var binding: ActivityAgregarMascotaBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityAgregarMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarMascota)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        database = FirebaseDatabase.getInstance().getReference("Mascotas")

        binding.btnVolver.setOnClickListener{
            intent= Intent(this,funcionalidades::class.java)
            startActivity(intent)
        }

        binding.btnGuardarMascota.setOnClickListener {
            val nombre = binding.etNombreMascota.text.toString()
            val especie = binding.etEspecieMascota.text.toString()
            val edad = binding.etEdadMascota.text.toString()

            val id= database.child("mascota").push().key

            if (nombre.isEmpty()){
                binding.etNombreMascota.error = "Ingrese el nombre de la mascota"
                return@setOnClickListener
            }
            if (especie.isEmpty()){
                binding.etEspecieMascota.error = "Ingrese la especie de la mascota"
                return@setOnClickListener
            }
            if (edad.isEmpty()) {
                binding.etEdadMascota.error = "Ingrese la edad de la mascota"
                return@setOnClickListener
            }
            val mascota= mascota(id,nombre,especie,edad)
            database.child(id!!).setValue(mascota)
                .addOnSuccessListener {
                    binding.etNombreMascota.setText("")
                    binding.etEspecieMascota.setText("")
                    binding.etEdadMascota.setText("")
                    Snackbar.make(binding.root, "Mascota agregada", Snackbar.LENGTH_SHORT).show()
                }

        }

    }
}