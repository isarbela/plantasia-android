package com.example.plantasia

import androidx.annotation.WorkerThread
import com.example.plantasia.repository.Plant
import com.example.plantasia.repository.dao.PlantDao
import kotlinx.coroutines.flow.Flow

class PlantRoomRepository(private val plantDao: PlantDao) {

        val allPlants: Flow<List<Plant>> = plantDao.getPlantsList()

        @WorkerThread
        suspend fun insert(plant: Plant) {
            plantDao.insert(plant)
        }

    }