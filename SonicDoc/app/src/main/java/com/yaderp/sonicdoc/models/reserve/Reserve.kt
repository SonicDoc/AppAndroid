package com.yaderp.sonicdoc.models.reserve

data class Reserve (
    val ID:Int,
    val Nombre:String?,
    val Fecha:String?,
    val Turno:String?,
    val Sexo:Int,
    val Estado:Int
)