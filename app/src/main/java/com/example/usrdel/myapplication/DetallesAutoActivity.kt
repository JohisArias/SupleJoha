package com.example.usrdel.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_auto.*


class DetallesAutoActivity : AppCompatActivity() {

    var auto: Auto? = null
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_auto)

        auto = intent.getParcelableExtra("detallesAuto")


        txtShowNombreP.text = auto?.nombre
        txtShowMarca.text = auto?.marca
        txtShowColor.text = auto?.color
        txtShowTipo.text = auto?.tipo
        txtShowAnio.text = auto?.anio.toString()
        txtShowPrecio.text = auto?.precio
        txtShowNumeroDuenios.text = auto?.numeroDuenios.toString()
        txtShowMatriculado.text = auto?.matriculadoActual.toString()

        myBitmapAgain = decodeBase64(auto?.imagenAuto.toString()!!)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


}
