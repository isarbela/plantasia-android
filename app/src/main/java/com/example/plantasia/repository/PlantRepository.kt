package com.example.plantasia.repository

import android.content.res.Resources
import android.util.Log
import com.example.plantasia.R
import com.example.plantasia.repository.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantRepository() {

    private val _appkey: String = Resources.getSystem().getString(R.string.apikey)
    fun getPlantDetails(id: String, cb: PlantDetailsCallback) {
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
        RetrofitInstance.api.getPlantsList(page, _appkey)?.enqueue(object : Callback<List<Plant>?> {

            override fun onResponse(call: Call<List<Plant>?>?, response: Response<List<Plant>?>?) {
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

            override fun onFailure(call: Call<List<Plant>?>?, t: Throwable) {
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
        fun onSuccess(plant: List<Plant>?)
        fun onError(errorMessage: String?)
    }
}
