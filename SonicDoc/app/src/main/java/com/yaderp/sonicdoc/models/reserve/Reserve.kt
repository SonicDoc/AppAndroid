package com.yaderp.sonicdoc.models.reserve

import com.google.gson.annotations.SerializedName

data class Reserve (
    val ID:Int,
    val Nombre:String,
    val Fecha:String,
    val Turno:String,
    val Sexo:Int,
    val Estado:Int
)