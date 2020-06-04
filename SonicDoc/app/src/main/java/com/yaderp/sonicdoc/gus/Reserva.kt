package com.yaderp.sonicdoc.gus

import com.google.gson.annotations.SerializedName

class Reserva(
    @SerializedName("ID")
    val ID:Int = 0,
    @SerializedName("Nombre")
    val nombre:String?
    /*@SerializedName("Fecha")
    val fecha:String?,
    @SerializedName("Turno")
    val turno:String?,
    @SerializedName("Sexo")
    val sexo:String?,
    @SerializedName("Estado")
    val estado:String?*/
)