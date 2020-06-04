package com.yaderp.sonicdoc.models.reserve

import com.google.gson.annotations.SerializedName

class Reserve (
    @SerializedName("ID")
    val ID:Int,
    @SerializedName("Nombre")
    val Nombre:String,
    @SerializedName("Fecha")
    val Fecha:String,
    @SerializedName("Turno")
    val Turno:String,
    @SerializedName("Sexo")
    val Sexo:Int,
    @SerializedName("Estado")
    val Estado:Int
)