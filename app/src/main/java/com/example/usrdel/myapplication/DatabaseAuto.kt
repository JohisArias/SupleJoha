package com.example.usrdel.myapplication

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.string
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.*
import org.xml.sax.Parser

class DatabaseAuto{
    companion object {

        fun insertarAuto(auto: Auto){
            "http://localhost:1337/Auto".httpPost(listOf("nombre" to auto.nombre, "marca" to auto.marca, "color" to auto.color, "tipo" to auto.tipo, "anio" to auto.anio, "precio" to auto.precio, "numeroDuenios" to auto.numeroDuenios, "matriculado" to auto.matriculadoActual, "imagenAuto" to auto.imagenAuto ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }


        fun getAutoList(conductorId: Int): ArrayList<Auto> {
            val auto: ArrayList<Auto> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://localhost:1337/Auto?".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val marca = it["marca"] as String
                val color = it["color"] as String
                val tipo = it["tipo"] as String
                val anio = it["anio"] as Int
                val precio = it["precio"] as String
                val numeroDuenios = it["numeroDuenios"] as Int
                val matriculado = it["matriculado"] as Int
                val imagenAuto = it["imagenAuto"] as String

                val carros = Auto(id, nombre, marca, color, tipo, anio, precio, numeroDuenios, matriculado, imagenAuto, 0, 0)
                auto.add(carros)
            }
            return auto
        }




    }
}