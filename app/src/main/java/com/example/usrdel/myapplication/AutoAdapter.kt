package com.example.usrdel.myapplication

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class AutoAdapter(private val autoList: List<Auto>) :  RecyclerView.Adapter<AutoAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombres: TextView
        var marca : TextView
        var color : TextView
        var tipo : TextView
        var anio : TextView
        var precio: TextView
        var guardar: Button

        lateinit var auto: Auto

        init {
            nombres = view.findViewById(R.id.txtNombre) as TextView
            marca = view.findViewById(R.id.txtMarca) as TextView
            color = view.findViewById(R.id.txtColor) as TextView
            tipo = view.findViewById(R.id.txtTipo) as TextView
            anio = view.findViewById(R.id.txtAnios) as TextView
            precio = view.findViewById(R.id.txtPrecio) as TextView
            guardar = view.findViewById(R.id.btnGuardar) as Button
            view.setOnCreateContextMenuListener(this)
        }

    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoN = autoList[position]
        holder.nombres.text = productoN.nombre
        holder.marca.text = productoN.marca
        holder.precio.text = productoN.precio
        holder.auto = productoN
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesAutoActivity::class.java)
            intent.putExtra("detallesAuto", productoN)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return autoList.size
    }


}