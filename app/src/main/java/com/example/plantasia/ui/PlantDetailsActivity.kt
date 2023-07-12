package com.example.plantasia.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.plantasia.R
import com.example.plantasia.repository.Plant
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.plantasia.PlantasiaApplication

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

        val id = intent.getIntExtra("plant", 0)

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


        detailsViewModel.getPlant(id).asLiveData().observe(this) { planta ->
            if (planta != null) {
                Log.i("PlantDetails", planta.toString())
                plantnameTV.text = planta.name
                commonNameTV.text = String.format(resources.getString(R.string.Common_name_label), planta.common_name)
                ageTV.text = String.format(resources.getString(R.string.Age_label), planta.age)
                cycleTV.text = String.format(resources.getString(R.string.Cycle_label), planta.cycle)
                wateringTV.text = String.format(resources.getString(R.string.Watering_label), planta.watering)
                indoorTV.text = String.format(resources.getString(R.string.Indoor_label), planta.indoor)
                carelevelTV.text = String.format(resources.getString(R.string.CareLevel_label), planta.care_level)
            }
        }

        deleteButton.setOnClickListener {
            detailsViewModel.deletePlant(id)
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