package com.yaderp.sonicdoc.gus

import com.yaderp.sonicdoc.models.reserve.Reserve
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ReservaService {
    @GET("Usuario/3")
    fun getAll():Call<List<Reserve>>
}