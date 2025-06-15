package com.example.calculadoraproyectoandre
// Esta madre es paraa importar cosas del menu
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    //Variables del pinche menu
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle


    val SUMA = "+"
    val RESTA = "-"
    val MULTIPLICACION = "*"
    val DIVISION = "/"
    val PORCENTAJE = "%"

    var operacionActual = ""

    var primerNumero: Double = Double.NaN
    var segundoNumero: Double = Double.NaN

    lateinit var tvTemp: TextView
    lateinit var tvResult: TextView

    lateinit var formatoDecimal: DecimalFormat



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        formatoDecimal = DecimalFormat("#.##########")
        tvTemp = findViewById(R.id.tvTemp)
        tvResult = findViewById(R.id.tvResult)

        // Conectamos el DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navigation_view)

        // Activamos el icono de hamburguesa
        toggle = ActionBarDrawerToggle(this, drawerLayout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Mostramos el icono en el ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Opcional: manejar los clics del menú
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_calculadora -> {
                    // Ya estás en calculadora
                    true
                }
                R.id.menu_conversor -> {
                    // Abre tu ConversorActivity
                    startActivity(Intent(this, ConversorActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    // Esto permite que el toggle funcione al tocar el icono
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    fun cambiarOperador(b: View) {
        if (tvTemp.text.isNotEmpty() || primerNumero.toString() != "NaN") {
            calcular()
            val boton: Button = b as Button

            when (boton.id) {
                R.id.btnDividir -> operacionActual = DIVISION
                R.id.btnMultiplicar -> operacionActual = MULTIPLICACION
                R.id.btnRestar -> operacionActual = RESTA
                R.id.btnSumar -> operacionActual = SUMA
                R.id.btnPorcentaje -> operacionActual = PORCENTAJE
                else -> operacionActual = boton.text.toString().trim()
            }

            if (tvTemp.text.toString().isEmpty()) {
                tvTemp.text = tvResult.text
            }

            tvResult.text = formatoDecimal.format(primerNumero) + operacionActual
            tvTemp.text = ""
        }
    }

    fun calcular() {
        try {
            if (primerNumero.toString() != "NaN") {
                if (tvTemp.text.toString().isEmpty()) {
                    tvTemp.text = tvResult.text.toString()
                }
                segundoNumero = tvTemp.text.toString().toDouble()
                tvTemp.text = ""

                when (operacionActual) {
                    SUMA -> primerNumero += segundoNumero
                    RESTA -> primerNumero -= segundoNumero
                    MULTIPLICACION -> primerNumero *= segundoNumero
                    DIVISION -> primerNumero /= segundoNumero
                    PORCENTAJE -> primerNumero %= segundoNumero
                }
            } else {
                primerNumero = tvTemp.text.toString().toDouble()
            }
        } catch (e: Exception) {
            // Silenciado
        }
    }

    fun seleccionarNumero(b: View) {
        val boton: Button = b as Button
        tvTemp.text = tvTemp.text.toString() + boton.text.toString()
    }

    fun igual(b: View) {
        calcular()
        tvResult.text = formatoDecimal.format(primerNumero)
        operacionActual = ""
    }

    fun borrar(b: View) {
        val boton: Button = b as Button
        when (boton.id) {
            R.id.botonClear -> {
                if (tvTemp.text.toString().isNotEmpty()) {
                    val datosActuales: CharSequence = tvTemp.text
                    tvTemp.text = datosActuales.subSequence(0, datosActuales.length - 1)
                } else {
                    primerNumero = Double.NaN
                    segundoNumero = Double.NaN
                    tvTemp.text = ""
                    tvResult.text = ""
                }
            }
            R.id.botonClearTodo -> {
                primerNumero = Double.NaN
                segundoNumero = Double.NaN
                tvTemp.text = ""
                tvResult.text = ""
            }
        }
    }
}
