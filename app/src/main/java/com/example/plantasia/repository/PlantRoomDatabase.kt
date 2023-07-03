package com.example.plantasia.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.plantasia.repository.PlantRoomDatabase.Companion.INSTANCE
import com.example.plantasia.repository.dao.PlantDao
import com.example.plantasia.utils.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlantRoomDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        var INSTANCE: PlantRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PlantRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                        PlantRoomDatabase::class.java,
                        "plant_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(PlantDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
                }
            }
        }
    }
private class PlantDatabaseCallback(
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
            scope.launch(Dispatchers.IO) {
                populateDatabase(database.plantDao())
            }
        }
    }

    suspend fun populateDatabase(plantDao: PlantDao) {
        // Delete all content here.
        plantDao.deleteAll()

        // Add sample plants.
        var plant = Plant(name="Cazalbé", common_name = "Daisy", age = 2)
        plantDao.insert(plant)
        plant = Plant(name = "Junior", common_name = "Maple", age = 1)
        plantDao.insert(plant)
    }
}

