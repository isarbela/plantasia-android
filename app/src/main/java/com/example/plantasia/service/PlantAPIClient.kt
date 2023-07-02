package com.example.plantasia.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
   val api : PlantInterface by lazy {
       Retrofit.Builder()
           .baseUrl("https://perenual.com/api/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(PlantInterface::class.java)
   }
}