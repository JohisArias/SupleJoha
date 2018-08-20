package com.example.usrdel.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            irActividadRgistrar()

    }

    fun irActividadRgistrar(){

            startActivity(intent)
        }
}