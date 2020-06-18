package com.yaderp.appsoft.api

import com.yaderp.appsoft.models.*
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("Usuario/{ID}")
    fun userReserva( @Path("ID")ID:Int):Call<List<ReservaResponse>>

    @FormUrlEncoded
    @POST("Reserva")
    fun setReserva(
        @Field("ID_Doctor") ID_Doctor:Int,
        @Field("ID_User") ID_User: Int,
        @Field("Fecha") Fecha: String,
        @Field("ID_Turno") ID_Turno: Int
    ): Call<DefaultRespuesta>

    @FormUrlEncoded
    @POST("Login")
    fun userLogin(
        @Field("Username") username:String,
        @Field("Password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("Turno")
    fun getTurno(
        @Field("ID") ID:Int,
        @Field("Nombres") Nombres: String
    ): Call<String>

    @GET("Doctor/{ID}")
    fun docReserva( @Path("ID")ID:Int):Call<List<ReservaResponse>>

    @GET("Especialidad")
    fun getEspecialidad():Call<List<Tipo>>

    @GET("Especialidad/{ID}")
    fun getDoctor(@Path("ID")ID:Int):Call<List<Doctor>>

    @GET("Turno")
    fun getTurnoDoc(
        @Field("ID") ID:Int,
        @Field("Nombres") Nombres: String
    ): Call<List<Tipo>>

    @GET("Login")
    fun getAllTurno(): Call<List<Tipo>>
}