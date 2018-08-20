package com.example.usrdel.myapplication

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.content.ContextCompat.startActivity

class Auto(var id: Int,
           var nombre: String,
           var marca: String,
           var color: String,
           var tipo: String,
           var anio: Int,
           var precio: String,
           var numeroDuenios: Int,
           var matriculadoActual: Int,
           var imagenAuto:String,
           var createdAt: Long,
           var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(marca)
        parcel.writeString(color)
        parcel.writeString(tipo)
        parcel.writeInt(anio)
        parcel.writeString(precio)
        parcel.writeInt(numeroDuenios)
        parcel.writeInt(matriculadoActual)
        parcel.writeString(imagenAuto)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Auto> {
        override fun createFromParcel(parcel: Parcel): Auto {
            return Auto(parcel)
        }

        override fun newArray(size: Int): Array<Auto?> {
            return arrayOfNulls(size)
        }
    }



}
