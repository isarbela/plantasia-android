package com.example.plantasia.service

data class Plant(
    val common_name: String? = null,
    val scientific_name: Array<String>? = null,
    val cycle: String? = null,
    val watering: String? = null,
    val edible: String? = null,
    val poisonous: String? = null,
    val age: Number = 0
)