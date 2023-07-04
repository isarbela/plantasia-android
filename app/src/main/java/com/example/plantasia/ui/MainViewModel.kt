package com.example.plantasia.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantasia.PlantRoomRepository
import com.example.plantasia.repository.Plant
import com.example.plantasia.repository.PlantRepository

class MainViewModel() : ViewModel() {
    val plantsList = MutableLiveData<List<Plant>?>()
    private var repo : PlantRepository = PlantRepository()

    fun getPlantsList(page: Int = 1) {
        repo.getPlantsList(page, object : PlantRepository.PlantsListCallback {
            override fun onSuccess(plants: List<Plant>?) {
                Log.d("MainViewModel", plants.toString())
                plantsList.value = plants
            }

            override fun onError(errorMessage: String?) {
                Log.d("MainViewModel", errorMessage!!)
            }
        })
    }
}

class MainViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}