package com.example.plantasia.service

import com.google.gson.annotations.SerializedName

class Plant {

    @SerializedName("common_name")
    private val commonName: String? = null
        get() = field

    @SerializedName("scientific_nane")
    private val scientificName: String? = null
        get() = field

    @SerializedName("cycle")
    private val cycle: String? = null
        get() = field

    @SerializedName("watering")
    private val watering: String? = null
        get() = field

    @SerializedName("edible")
    private val edible: String? = null
        get() = field

    @SerializedName("poisonous")
    private val poisonous: String? = null
        get() = field

    @SerializedName("hardiness")
    private val hardiness: String? = null
        get() = field

    @SerializedName("age")
    private val age: Number = 0
        get() = field

    override fun toString(): String {
        return "Plant{" +
                "common name='" + commonName + '\'' +
                ", scientific name='" + scientificName + '\'' +
                ", cycle='" + cycle + '\'' +
                ", watering='" + watering + '\'' +
                ", edible='" + edible + '\'' +
                ", poisonous='" + poisonous + '\'' +
                ", hardiness='" + hardiness + '\'' +
                ", poisonous='" + age + '\'' +
                '}'
    }

}