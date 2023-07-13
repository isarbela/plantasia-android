package com.example.plantasia.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.plantasia.R
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import com.example.plantasia.PlantasiaApplication
import com.example.plantasia.repository.Plant

class PlantDetailsActivity : AppCompatActivity() {

    private val detailsViewModel: PlantDetailsViewModel by viewModels {
        PlantDetailsViewModelFactory((application as PlantasiaApplication).repository)
    }

    private lateinit var plantnameTV: TextView
    private lateinit var commonNameTV: TextView
    private lateinit var scientificNameTV: TextView
    private lateinit var cycleTV: TextView
    private lateinit var wateringTV: TextView
    private lateinit var indoorTV: TextView
    private lateinit var carelevelTV: TextView
    private lateinit var ageTV: TextView
    private lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_plant_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val plant: Plant = intent.getSerializableExtra("plant") as Plant

        //Setting Views
        plantnameTV = findViewById(R.id.plantNameTV)
        commonNameTV = findViewById(R.id.commonNameTV)
        scientificNameTV = findViewById(R.id.scientificNameTV)
        cycleTV = findViewById(R.id.cycleTV)
        wateringTV = findViewById(R.id.wateringTV)
        indoorTV = findViewById(R.id.indoorTV)
        carelevelTV = findViewById(R.id.carelevelTV)
        ageTV = findViewById(R.id.ageTV)
        deleteButton = findViewById(R.id.deleteButton)


        detailsViewModel.getPlant(plant.Roomid).asLiveData().observe(this) { planta ->
            if (planta != null) {
                Log.i("PlantDetailsRoom", planta.toString())
                plantnameTV.text = planta.name
                commonNameTV.text = String.format(resources.getString(R.string.Common_name_label), planta.common_name)
                scientificNameTV.text = String
                    .format(resources.getString(R.string.labelDetailsScientificName),
                       if (!planta.scientific_name.isNullOrEmpty()) planta.scientific_name.toString().filter { it != '[' && it != ']'}
                       else resources.getString(R.string.notAvailable))
                ageTV.text = String.format(resources.getString(R.string.Age_label), planta.age)
                cycleTV.text = String.format(resources.getString(R.string.Cycle_label), planta.cycle ?: resources.getString(R.string.notAvailable))
                wateringTV.text = String.format(resources.getString(R.string.Watering_label), planta.watering ?: resources.getString(R.string.notAvailable))
                indoorTV.text = String.format(resources.getString(R.string.Indoor_label), planta.indoor ?: resources.getString(R.string.notAvailable))
                carelevelTV.text = String.format(resources.getString(R.string.CareLevel_label), planta.care_level ?: resources.getString(R.string.notAvailable))
            }
        }
        detailsViewModel.plantDetails.observe(this) { planta ->
            if (planta != null) {
                Log.i("PlantDetailsWebAPI", planta.toString())
                scientificNameTV.text = String
                    .format(resources.getString(R.string.labelDetailsScientificName),
                        if (!planta.scientific_name.isNullOrEmpty()) planta.scientific_name.toString().filter { it != '[' && it != ']'}
                        else resources.getString(R.string.notAvailable))
                cycleTV.text = String.format(resources.getString(R.string.Cycle_label), planta.cycle ?: resources.getString(R.string.notAvailable))
                wateringTV.text = String.format(resources.getString(R.string.Watering_label), planta.watering ?: resources.getString(R.string.notAvailable))
                indoorTV.text = String.format(resources.getString(R.string.Indoor_label), planta.indoor ?: resources.getString(R.string.notAvailable))
                carelevelTV.text = String.format(resources.getString(R.string.CareLevel_label), planta.care_level ?: resources.getString(R.string.notAvailable))
            }
        }
        detailsViewModel.getPlantDetails(plant.id + 1)

        deleteButton.setOnClickListener {
            detailsViewModel.deletePlant(plant.Roomid)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}