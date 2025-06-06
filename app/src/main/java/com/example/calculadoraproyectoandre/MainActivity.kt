package com.example.calculadoraproyectoandre

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    val SUMA = "+"
    val RESTA = "-"
    val MULTIPLICACION = "x"
    val DIVISION = "/"
    val PORCENTAJE = "%"

    var operacionActual = ""

    var primerNumero:Double = Double.NaN
    var segundoNumero:Double = Double.NaN

    lateinit var tvTemporal:TextView
    lateinit var tvResult:TextView

    lateinit var formatoDecimal:DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        formatoDecimal = DecimalFormat("#.##########")
        tvTemporal = findViewById(R.id.tvTemporal)
        tvResult = findViewById(R.id.tvResult)
    }

    fun cambiarOperador(b: View){
        val boton:Button = b as Button
        if(boton.text.toString().trim()== "÷"){
            operacionActual = "/"
        }else if(boton.text.toString().trim()=="X"){
            operacionActual = "*"
        }else{
            operacionActual = boton.text.toString().trim()
        }
    }

    fun calcular(b: View){
        if(Double.NaN != primerNumero){
            segundoNumero = tvTemporal.text.toString().toDouble()
            tvTemporal.text = ""

            when(operacionActual){
                "+" -> primerNumero = (primerNumero + segundoNumero)
                "-" -> primerNumero = (primerNumero - segundoNumero)
                "*" -> primerNumero = (primerNumero * segundoNumero)
                "/" -> primerNumero = (primerNumero / segundoNumero)
                "%" -> primerNumero = (primerNumero % segundoNumero)
            }
        }else{
            primerNumero = tvTemporal.text.toString().toDouble()
        }
    }
}