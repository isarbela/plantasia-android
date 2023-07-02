package com.example.plantasia.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantasia.repository.Plant
import com.example.plantasia.repository.PlantRepository

class PlantDetailsViewModel() : ViewModel() {

    val plantDetails = MutableLiveData<Plant?>()
    private var repo : PlantRepository ? = PlantRepository()


    fun getPlantDetails(id: String) {
        repo?.getPlantDetails(id, object : PlantRepository.PlantDetailsCallback {
            override fun onSuccess(plant: Plant?) {
                Log.d("MainViewModel", plant.toString())
                plantDetails.value = plant
            }

            override fun onError(errorMessage: String?) {
                Log.d("MainViewModel", errorMessage!!)
            }
        })
    }
}