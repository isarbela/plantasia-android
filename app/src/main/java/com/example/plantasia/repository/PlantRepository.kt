package com.example.plantasia.repository

import android.util.Log
import com.example.plantasia.BuildConfig
import com.example.plantasia.repository.retrofit.PlantAPIResponse
import com.example.plantasia.repository.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantRepository() {

    private val _appkey: String = BuildConfig.API_KEY
    fun getPlantDetails(id: Int, cb: PlantDetailsCallback) {
        RetrofitInstance.api.getPlantDetails(id, _appkey)?.enqueue(object : Callback<Plant?> {

            override fun onResponse(call: Call<Plant?>?, response: Response<Plant?>?) {
                if (response?.body() != null) {
                    if (!response.isSuccessful) {
                        cb.onError(response.message())
                    } else cb.onSuccess(
                        response.body()
                    )
                } else {
                    cb.onError(response?.message())
                }
            }

            override fun onFailure(call: Call<Plant?>?, t: Throwable) {
                cb.onError(t.message)
                Log.d("TAG", t.message.toString())
            }
        })
    }

    fun getPlantsList(page: Int = 0, cb: PlantsListCallback) {
        RetrofitInstance.api.getPlantsList(page, _appkey).enqueue(object : Callback<PlantAPIResponse?> {

            override fun onResponse(call: Call<PlantAPIResponse?>?, response: Response<PlantAPIResponse?>?) {
                Log.v("PlantRepo", response?.body().toString())
                if (response?.body() != null) {
                    if (!response.isSuccessful) {
                        cb.onError(response.message())
                    } else cb.onSuccess(
                        response.body()
                    )
                } else {
                    cb.onError(response?.message())
                }
            }

            override fun onFailure(call: Call<PlantAPIResponse?>?, t: Throwable) {
                cb.onError(t.message)
                Log.d("TAG", t.message.toString())
            }
        })
    }

    interface PlantDetailsCallback {
        fun onSuccess(plant: Plant?)
        fun onError(errorMessage: String?)
    }

    interface PlantsListCallback {
        fun onSuccess(plant: PlantAPIResponse?)
        fun onError(errorMessage: String?)
    }
}
