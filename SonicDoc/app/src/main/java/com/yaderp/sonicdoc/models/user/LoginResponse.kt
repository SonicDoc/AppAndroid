package com.yaderp.sonicdoc.models.user

data class LoginResponse (
    val ID:Int,
    val Estado:Int,
    val Error: Boolean,
    val Mensaje:String?
)