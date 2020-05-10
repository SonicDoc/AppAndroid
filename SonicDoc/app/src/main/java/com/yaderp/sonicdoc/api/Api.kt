package com.yaderp.sonicdoc.api

import com.yaderp.sonicdoc.models.Tipo
import com.yaderp.sonicdoc.models.reserve.Reserve
import com.yaderp.sonicdoc.models.user.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("Login")
    fun userLogin(
        @Field("Username") username:String,
        @Field("Password") password: String
    ): Call<LoginResponse>


    @FormUrlEncoded
    @GET("Usuario/{ID}")
    fun reserveUser(@Path("ID") id:Int):Call<List<Reserve>>

}