package com.example.pfoodgauge

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pfoodgauge.databinding.ActivityFuncionalidadesBinding
import com.example.pfoodgauge.databinding.ActivityLoginBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class funcionalidades : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityFuncionalidadesBinding
    private lateinit var auth: FirebaseAuth

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

        auth= Firebase.auth
        val toolbar: androidx.appcompat.widget.Toolbar= findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer=findViewById(R.id.mainf)

        toggle= ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)





        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val cambiarPantallac: TextView = findViewById(R.id.btn_rellenar_comida)
        cambiarPantallac.setOnClickListener { val intent = Intent(this, rellenar_comida::class.java)
            startActivity(intent)}

        val cambiarPantallad: TextView = findViewById(R.id.btnDetalles)
        cambiarPantallad.setOnClickListener { val intent = Intent(this, temperatura::class.java)
            startActivity(intent)}

        val cambiarPantallaa: TextView = findViewById(R.id.btn_rellenar_Agua)
        cambiarPantallaa.setOnClickListener { val intent = Intent(this, rellenar_agua::class.java)
            startActivity(intent)}

        val sharedPreferencesAgua = getSharedPreferences("pAgua", Context.MODE_PRIVATE)
        val cantidadAgua = sharedPreferencesAgua.getInt("aguag", 0)

        val sharedPreferencesComida = getSharedPreferences("pComida", Context.MODE_PRIVATE)
        val cantidadComida = sharedPreferencesComida.getInt("comidag", 0)

        val cantidadAguatv: TextView = findViewById(R.id.tvporcentajeAgua)
        cantidadAguatv.text = "$cantidadAgua mL"

        val cantidadComidatv: TextView= findViewById(R.id.tvporcentajeAlimento)
        cantidadComidatv.text= "$cantidadComida g"

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
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut(){
        Firebase.auth.signOut()
        Toast.makeText(this,"sesion cerrada",Toast.LENGTH_SHORT).show()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}