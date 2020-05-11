package com.yaderp.sonicdoc.api

import com.yaderp.sonicdoc.models.Tipo
import com.yaderp.sonicdoc.models.reserve.Reserve
import com.yaderp.sonicdoc.models.user.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    //llamado post del login  FUncionando correctamente
    @FormUrlEncoded
    @POST("Login")
    fun userLogin(
        @Field("Username") username:String,
        @Field("Password") password: String
    ): Call<LoginResponse>

    // Llamada de lista de reservas de un usuario que es un alista de doctores
    // actualmente en el api solo existe un usuario de ID = 3

    @FormUrlEncoded
    @GET("Usuario/{ID}")
    fun reserveUser(@Path("ID") id:Int):Call<List<Reserve>>


    // llamado la lista de reserva de un doctor misma informacion de usuario pero aqui trae de usuarios
    @FormUrlEncoded
    @GET("Doctor/{ID}")
    fun reserveDoctor(@Path("ID") id:Int):Call<List<Reserve>>

}