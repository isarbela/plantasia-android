package com.example.plantasia

import android.app.Application
import com.example.plantasia.repository.PlantRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PlantasiaApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { PlantRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PlantRoomRepository(database.plantDao()) }
    }
