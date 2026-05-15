package com.example.devaraa_kaaduu.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import com.example.devaraa_kaaduu.data.model.PlantResponse

interface PlantNetApi {

    @Multipart
    @POST("all")
    suspend fun identifyPlant(
        @Part images: MultipartBody.Part,
        @Part("organs") organs: RequestBody,
        @Query("api-key") apiKey: String
    ): PlantResponse
}