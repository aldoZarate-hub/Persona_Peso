package com.example.personapeso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var edad:Int = 25
    var peso:Int = 75
    var altura:Int = 180
    var sexo:String = "hola"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekAltura.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (progress <= 100){
                    seekAltura.setProgress(100)
                    tvNumAltura.text = "100"
                    altura = 100
                }else{
                    tvNumAltura.text = "$progress"
                    altura = progress
                }

                btnEdadMenos.setOnClickListener(this@MainActivity)
                btnEdadMas.setOnClickListener(this@MainActivity)
                btnPesoMas.setOnClickListener(this@MainActivity)
                btnPesoMenos.setOnClickListener(this@MainActivity)
                imageButtonFem.setOnClickListener(this@MainActivity)
                imageButtonMasc.setOnClickListener(this@MainActivity)
                btnCalcular.setOnClickListener(this@MainActivity)

                tvPeso.text = "$peso"
                tvEdad.text = "$edad"
                tvNumAltura.text = "$altura"


            }



            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            //Botones edad
            R.id.btnEdadMenos->{
                edad--
                tvEdad.text = "$edad"
            }
            R.id.btnEdadMas->{
                edad++
                tvEdad.text = "$edad"
            }
            //Botones peso
            R.id.btnPesoMas->{
                peso++
                tvPeso.text = "$peso"
            }
            R.id.btnPesoMenos->{
                peso--
                tvPeso.text = "$peso"
            }
            R.id.imageButtonFem->sexo = "f"
            R.id.imageButtonMasc->sexo = "m"

            R.id.btnCalcular->{
                val imc = calculaIMC(peso,altura)
                //Eso nos deja que nos mande a otra activity
                val intencion=Intent(this,ActivityResultados::class.java)
                intencion.putExtra("imc",imc)
                intencion.putExtra("sexo", sexo)
                intencion.putExtra("edad", edad)
                startActivity(intencion)
            }

        }
    }
    fun calculaIMC(peso:Int, altura:Int):Float{
        val pesoF = peso.toFloat()
        val alturaF = altura.toFloat()/100
        var imc = pesoF/(alturaF*alturaF)
        return imc.toFloat()

    }
    fun interpretaIMC(imc:Float, edad:Int, sexo:String) {
        var mensaje:String = ""
        if (sexo == "f") {
            when (edad) {
                in 19..24 -> {
                    when (imc) {
                        in 18.9..22.1 -> {
                            mensaje = "Excelente"

                        }
                        in 22.2..30.7->{
                            mensaje = "Estas bien mal"
                        }

                    }
                }
            }
        }
    }
}