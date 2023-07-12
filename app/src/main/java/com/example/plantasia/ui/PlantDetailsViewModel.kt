package com.example.plantasia.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.plantasia.PlantRoomRepository
import com.example.plantasia.repository.Plant
import com.example.plantasia.repository.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PlantDetailsViewModel(private val repository: PlantRoomRepository) : ViewModel() {

    val plantDetails = MutableLiveData<Plant?>()
    private var repo : PlantRepository = PlantRepository()


    fun getPlantDetails(id: String) {
        repo.getPlantDetails(id, object : PlantRepository.PlantDetailsCallback {
            override fun onSuccess(plant: Plant?) {
                Log.d("MainViewModel", plant.toString())
                plantDetails.value = plant
            }

            override fun onError(errorMessage: String?) {
                Log.d("MainViewModel", errorMessage!!)
            }
        })
    }


   fun getPlant(id: Int): Flow<Plant> {
        return repository.getPlant(id)
    }

     fun deletePlant(id: Int) = viewModelScope.launch {
        repository.deletePlant(id)
    }
}

class PlantDetailsViewModelFactory(private val repository: PlantRoomRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlantDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}