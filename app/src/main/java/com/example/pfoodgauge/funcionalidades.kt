package com.example.pfoodgauge

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pfoodgauge.databinding.ActivityFuncionalidadesBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*

class funcionalidades : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityFuncionalidadesBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFuncionalidadesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainf)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.mainf)
        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val cambiarPantallac: TextView = findViewById(R.id.btn_rellenar_comida)
        cambiarPantallac.setOnClickListener {
            val intent = Intent(this, rellenar_comida::class.java)
            startActivity(intent)
        }

        val cambiarPantallad: TextView = findViewById(R.id.btnDetalles)
        cambiarPantallad.setOnClickListener {
            val intent = Intent(this, temperatura::class.java)
            startActivity(intent)
        }

        val cambiarPantallaa: TextView = findViewById(R.id.btn_rellenar_Agua)
        cambiarPantallaa.setOnClickListener {
            val intent = Intent(this, rellenar_agua::class.java)
            startActivity(intent)
        }

        database = FirebaseDatabase.getInstance().reference

        mostrarDatosFirebase()
    }

    private fun mostrarDatosFirebase() {
        val cantidadAguatv: TextView = findViewById(R.id.tvporcentajeAgua)
        val cantidadComidatv: TextView = findViewById(R.id.tvporcentajeAlimento)
        val temperaturatv: TextView = findViewById(R.id.porcentajeTemperatura)

        database.child("/agua/medicion/volumen_litros")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val cantidadAgua = snapshot.getValue(Double::class.java)
                    cantidadAguatv.text = if (cantidadAgua != null) {
                        "$cantidadAgua L"
                    } else {
                        "Error al obtener agua"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@funcionalidades,
                        "Error: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        database.child("/comida/medicion/peso_gramos")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val cantidadComida = snapshot.getValue(Double::class.java)
                    cantidadComidatv.text = if (cantidadComida != null) {
                        "$cantidadComida g"
                    } else {
                        "Error al obtener comida"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@funcionalidades,
                        "Error: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        database.child("temperatura/medicion/temperatura")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val temperatura = snapshot.getValue(Double::class.java)
                    if (temperatura != null) {
                        temperaturatv.text = "$temperatura°C"
                    } else {
                        temperaturatv.text = "Error al obtener temperatura"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@funcionalidades,
                        "Error: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, funcionalidades::class.java)
                startActivity(intent)
            }
            R.id.cambiarContra -> {
                val intent2 = Intent(this, cambiarContra::class.java)
                startActivity(intent2)
            }
            R.id.agregar_mascota -> {
                val intent3 = Intent(this, agregarMascota::class.java)
                startActivity(intent3)
            }
            R.id.ver_mascotas -> {
                val intent4 = Intent(this, verMascota::class.java)
                startActivity(intent4)
            }
            R.id.cerrarS -> {
                signOut()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
