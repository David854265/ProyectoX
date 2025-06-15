package com.example.calculadoraproyectoandre

import android.content.Intent
import android.view.MenuItem

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ConversorActivity : AppCompatActivity() {
    //Variables del pinche menu
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    lateinit var edtnumero1: EditText
    lateinit var btnkglibras: CheckBox
    lateinit var btnkilogramos: CheckBox
    lateinit var btnmillas: CheckBox
    lateinit var btnkilometros: CheckBox
    lateinit var operacion: TextView
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        // Drawer
        drawerLayout = findViewById(R.id.drawer_layout_conversor)
        navView = findViewById(R.id.navigation_view_conversor)

        // Activamos el icono de hamburguesa
        toggle = ActionBarDrawerToggle(this, drawerLayout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_calculadora -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.menu_conversor -> {
                    // Ya estás aquí
                    true
                }
                R.id.menu_creditos -> {
                    // Aquí puedes abrir otra actividad si la haces
                    true
                }
                else -> false
            }
        }



        edtnumero1 = findViewById(R.id.edtnumero1)
        btnkglibras = findViewById(R.id.btnlibras)
        btnkilogramos = findViewById(R.id.btnkilogramos)
        btnmillas = findViewById(R.id.btnmillas)
        btnkilometros = findViewById(R.id.btnkilometros)
        operacion = findViewById(R.id.operacion)
        resultado = findViewById(R.id.resultado)

        btnkglibras.setOnClickListener {
            val inputText = edtnumero1.text.toString()
            if (inputText.isNotEmpty()) {
                val num1 = inputText.toDouble()
                val conversionFactor = 2.2046
                val calculatedResult = num1 * conversionFactor
                operacion.text = "$num1 kg * $conversionFactor lbs/kg"
                resultado.text = "Resultado: %.2f lbs".format(calculatedResult)
            } else {
                operacion.text = "Ingresa un número"
                resultado.text = "Resultado: "
            }
        }

        btnkilogramos.setOnClickListener {
            val inputText = edtnumero1.text.toString()
            if (inputText.isNotEmpty()) {
                val num1 = inputText.toDouble()
                val conversionFactor = 0.4536
                val calculatedResult = num1 * conversionFactor
                operacion.text = "$num1 lbs * $conversionFactor kg"
                resultado.text = "Resultado: %.2f kg".format(calculatedResult)
            } else {
                operacion.text = "Ingresa un número"
                resultado.text = "Resultado: "
            }
        }

        btnmillas.setOnClickListener {
            val inputText = edtnumero1.text.toString()
            if (inputText.isNotEmpty()) {
                val num1 = inputText.toFloat()
                val conversionFactor = 0.621371f
                val calculatedResult = num1 * conversionFactor
                operacion.text = "$num1 km * $conversionFactor mi"
                resultado.text = "Resultado: %.2f millas".format(calculatedResult)
            } else {
                operacion.text = "Ingresa un número"
                resultado.text = "Resultado: "
            }
        }

        btnkilometros.setOnClickListener {
            val inputText = edtnumero1.text.toString()
            if (inputText.isNotEmpty()) {
                val num1 = inputText.toFloat()
                val conversionFactor = 1.60934f
                val calculatedResult = num1 * conversionFactor
                operacion.text = "$num1 mi * $conversionFactor km"
                resultado.text = "Resultado: %.2f km".format(calculatedResult)
            } else {
                operacion.text = "Ingresa un número"
                resultado.text = "Resultado: "
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
