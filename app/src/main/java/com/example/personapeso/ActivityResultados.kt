package com.example.personapeso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_resultados.*

class ActivityResultados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val intencion = intent

        val imc = intencion.getFloatExtra("imc", 0.0F)
        val sexo = intencion.getStringExtra("sexo" )
        val edad = intencion.getIntExtra("edad", 25)

        tvResultado.text = "$imc"
        Log.d("valor_imc","$imc")
        Log.d("valor_sexo","$sexo")
        Log.d("valor_edad","$edad")

        btnRecalcular.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}