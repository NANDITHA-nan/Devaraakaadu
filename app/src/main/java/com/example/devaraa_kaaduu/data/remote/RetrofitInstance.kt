package com.example.devaraa_kaaduu.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: PlantNetApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://my-api.plantnet.org/v2/identify/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlantNetApi::class.java)
    }
}