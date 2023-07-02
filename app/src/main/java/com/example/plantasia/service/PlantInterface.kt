package com.example.plantasia.service

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


public interface PlantInterface {
    @GET("species-list")
    fun getPlantsList(
        @Query("page") page: Number?,
        @Query("key") key: String?
    ): Call<Array<Plant>?>?

    @GET("species/details")
    fun getPlantDetails(
        @Query("id") id: String?,
        @Query("key") key: String?
    ): Call<Plant?>?
}