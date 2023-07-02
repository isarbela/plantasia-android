package com.example.plantasia

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.plantasia.service.Plant
import com.example.plantasia.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RetrofitInstance.api.getPlantDetails("1", "sk-hZ3g649f8c6a002541442")?.enqueue(object : Callback<Plant?> {
            override fun onResponse(call: Call<Plant?>?, response: Response<Plant?>?) {
                if (response?.body() != null) {
                    Log.i("DEUBOM", response.body().toString())
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<Plant?>?, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })

        }
    }

//    private fun isNetworkAvailable(): Boolean {
//        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        return networkCapabilities != null
//    }
