package com.yaderp.sonicdoc.api

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //punto final del api
    private const val BASE_URL = "http://yaderp.com/sonicdoc/api/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requesBuilder = original.newBuilder()
                .addHeader("Autorizacion","ydrsoft")
                .method(original.method(),original.body())

            val request = requesBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}