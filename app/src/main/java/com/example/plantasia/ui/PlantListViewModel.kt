package com.example.plantasia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantasia.PlantRoomRepository
import com.example.plantasia.repository.Plant
import kotlinx.coroutines.launch

class PlantViewModel(private val repository: PlantRoomRepository): ViewModel() {

    val allPlants: LiveData<List<Plant>> = repository.allPlants.asLiveData()

    fun insert(plant: Plant) = viewModelScope.launch {
        repository.insert(plant)
    }
}

class PlantViewModelFactory(private val repository: PlantRoomRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlantViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}