package com.example.plantasia.service

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantRepository {
//    public fun getPlantDetails(callback: PlantCallback) {
//        val client: PlantInterface =
//           PlantAPIClient.getClient()!!.create(PlantInterface::class.java)
//        val call: Call<Plant?>? = client.getPlantDetails(id = "1", key = "sk-hZ3g649f8c6a002541442")
//        call!!.enqueue(object : Callback<Plant?> {
//            override fun onResponse(call: Call<Plant?>, response: Response<Plant?>) {
//                callback.onSuccess(response.body())
//            }
//
//            override fun onFailure(call: Call<Plant?>, t: Throwable) {
//                callback.onError(t.message)
//            }
//        })
//    }

    // set a generic callback interface
    interface PlantCallback {
        fun onSuccess(plant: Plant?)
        fun onError(errorMessage: String?)
    }
}

private fun <T> Call<T>?.enqueue(callback: Callback<Plant?>) {

}
