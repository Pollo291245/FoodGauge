package com.example.pfoodgauge

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pfoodgauge.Adapter.AdapterMascota
import com.example.pfoodgauge.Models.mascota
import com.example.pfoodgauge.databinding.ActivityVerMascotaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class verMascota : AppCompatActivity() {

    private lateinit var binding: ActivityVerMascotaBinding
    private lateinit var database: DatabaseReference
    private lateinit var mascotasList: ArrayList<mascota>
    private lateinit var adaptermascota: AdapterMascota
    private lateinit var mascotaRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVerMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.verMascota)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mascotaRecyclerView = binding.rvMascotas
        mascotaRecyclerView.layoutManager = LinearLayoutManager(this)
        mascotaRecyclerView.hasFixedSize()
        mascotasList = arrayListOf<mascota>()

        getMascotas()
    }

    private fun getMascotas() {
        database= FirebaseDatabase.getInstance().getReference("Mascotas")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (mascotaSnapshot in snapshot.children) {
                        val mascota = mascotaSnapshot.getValue(mascota::class.java)
                        mascotasList.add(mascota!!)
                    }
                    adaptermascota = AdapterMascota(mascotasList)
                    mascotaRecyclerView.adapter = adaptermascota
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}