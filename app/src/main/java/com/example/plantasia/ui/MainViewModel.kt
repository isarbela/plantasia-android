package com.example.plantasia.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantasia.repository.Plant
import com.example.plantasia.repository.PlantRepository
import com.example.plantasia.repository.retrofit.PlantAPIResponse

class MainViewModel() : ViewModel() {
    val plantsList = MutableLiveData<List<Plant>?>()
    private val repo : PlantRepository = PlantRepository()

    fun getPlantsList(page: Int = 1) {
        repo.getPlantsList(page, object : PlantRepository.PlantsListCallback {
            override fun onSuccess(response: PlantAPIResponse?) {
                Log.d("MainViewModel", response?.data.toString())
                plantsList.value = response?.data
            }

            override fun onError(errorMessage: String?) {
                Log.d("MainViewModel [Error]", errorMessage!!)
            }
        })
    }

    override fun toString(): String {
        return "teste"
    }
}

class MainViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}