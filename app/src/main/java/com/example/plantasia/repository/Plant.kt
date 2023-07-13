package com.example.plantasia.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.FileDescriptor
import java.io.Serializable


@Entity(tableName = "plant_table")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val Roomid: Int = 0,
    @ColumnInfo(name= "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "common_name")
    val common_name: String? = null,
    @ColumnInfo(name="scientific_name")
    val scientific_name: ArrayList<String>? = null,
    @ColumnInfo(name = "cycle")
    val cycle: String? = null,
    @ColumnInfo(name = "watering")
    val watering: String? = null,
    @ColumnInfo(name = "indoor")
    val indoor: String? = null,
    @ColumnInfo(name = "care_level")
    val care_level: String? = null,
    @ColumnInfo(name = "age")
    val age: Int = 0,
    @ColumnInfo(name = "description")
    val description: String? = null
): Serializable