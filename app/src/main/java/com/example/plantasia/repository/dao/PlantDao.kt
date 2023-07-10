package com.example.plantasia.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.plantasia.repository.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
        @Query("SELECT * FROM plant_table ORDER BY name ASC")
        fun getPlantsList(): Flow<List<Plant>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(plant: Plant)

        @Query("DELETE FROM plant_table WHERE id = :id")
        suspend fun delete(id: String)

        @Query("DELETE FROM plant_table")
        suspend fun deleteAll()

        @Update
        suspend fun update(plant: Plant)

        @Query("SELECT * FROM plant_table WHERE id = :plantId")
        fun getPlant(plantId: Int): Flow<Plant>

}