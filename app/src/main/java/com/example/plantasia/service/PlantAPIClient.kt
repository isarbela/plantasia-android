package com.example.plantasia.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlantAPIClient {
    companion object {
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://perenual.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}