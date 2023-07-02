package com.example.plantasia.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PlantInterface {
    @GET("species-list")
    fun getPlantsList(
        @Query("page") page: Number?,
        @Query("key") key: String?
    ): Call<Array<Plant>>

    @GET("species/details/{id}")
     fun getPlantDetails(
        @Path("id") id: String?,
        @Query("key") key: String?
    ): Call<Plant?>?
}