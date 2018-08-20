package com.example.usrdel.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_auto.*
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.util.Base64


class AutoActivity : AppCompatActivity() {

    var auto: Auto? = null
    var idTienda: Int = 0
    private lateinit var imageBitmap: Bitmap
    var tipo = false
    lateinit var myBase64Image:String
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto)

        idTienda = intent.getIntExtra("conductorId", 0)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textView.text = "Editar Auto"
            auto = intent.getParcelableExtra("auto")
            fillFields()
            tipo = true
        }

        btnGuardar.setOnClickListener { v: View? ->
            crearAuto()
        }

    }

    fun fillFields() {
        txtNombre.setText(auto?.nombre)
        txtMarca.setText(auto?.marca)
        txtColor.setText(auto?.color)
        txtTipo.setText(auto?.tipo)
        txtAnios.setText(auto?.anio.toString())
        txtPrecio.setText(auto?.precio)
        txtNumeroDuenios.setText(auto?.numeroDuenios.toString())
        txtmatriculado.setText(auto?.matriculadoActual.toString())
    }



    val REQUEST_IMAGE_CAPTURE = 1


    fun crearAuto(){
        var nombreP = txtNombre.text.toString()
        var marca = txtMarca.text.toString()
        var color = txtColor.text.toString()
        var tipo = txtTipo.text.toString()
        var anio = txtAnios.text.toString().toInt()
        var precio = txtPrecio.text.toString()
        var numeroDuenios = txtNumeroDuenios.text.toString().toInt()
        var matriculado = txtmatriculado.text.toString().toInt()
        var imagenProducto = myBase64Image


            var pokemon = Auto(0, nombreP, marca, color, tipo, anio, precio, numeroDuenios, matriculado, imagenProducto, 0, 0)
            DatabaseAuto.insertarAuto(pokemon)
            //Toast.makeText(this,"SAVEDDD: $imagenAuto", Toast.LENGTH_SHORT).show()



        finish()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
       super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap

            myBase64Image = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100)
            myBitmapAgain = decodeBase64(myBase64Image)

            imageViewAuto.setImageBitmap(myBitmapAgain)

            //Toast.makeText(this,"Dentro de camara: $myBase64Image", Toast.LENGTH_SHORT).show()

        }

    }

    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat, quality: Int): String {
        val byteArrayOS = ByteArrayOutputStream()
        image.compress(compressFormat, quality, byteArrayOS)
        return android.util.Base64.encodeToString(byteArrayOS.toByteArray(), android.util.Base64.DEFAULT)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }




}

