package com.example.plantasia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import com.example.plantasia.R
import com.example.plantasia.repository.Plant
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider

class PlantDetailsActivity : AppCompatActivity() {

    private lateinit var detailsViewModel: PlantDetailsViewModel
    private lateinit var plantnameTV: TextView
    private lateinit var commonNameTV: TextView
    private lateinit var scientificNameLL: LinearLayout
    private lateinit var scientificNameTV: TextView
    private lateinit var cycleTV: TextView
    private lateinit var wateringTV: TextView
    private lateinit var indoorTV: TextView
    private lateinit var carelevelTV: TextView
    private lateinit var ageTV: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        detailsViewModel = ViewModelProvider(this)[PlantDetailsViewModel::class.java]

        val id = intent.getIntExtra("plant", 0)

        //Setting Views
        plantnameTV = findViewById(R.id.plantNameTV)
        commonNameTV = findViewById(R.id.commonNameTV)
        scientificNameTV = findViewById(R.id.scientificNameTV)
        scientificNameLL = findViewById(R.id.scientificNameLL)
        cycleTV = findViewById(R.id.cycleTV)
        wateringTV = findViewById(R.id.wateringTV)
        indoorTV = findViewById(R.id.indoorTV)
        carelevelTV = findViewById(R.id.carelevelTV)
        ageTV = findViewById(R.id.ageTV)

        detailsViewModel.getPlant().observe(this) { planta ->
            if (planta != null) {
                plantnameTV.text = planta.name
                commonNameTV.text = String().format(R.string.Common_name_label, planta.common_name)
                cycleTV.text = String().format(R.string.Cycle_label, planta.cycle)
                wateringTV.text = String().format(R.string.Watering_label, planta.watering)
                indoorTV.text = String().format(R.string.Indoor_label, planta.indoor)
                carelevelTV.text = String().format(R.string.CareLevel_label, planta.care_level)
                ageTV.text = String().format(R.string.Age_label, planta.age.toString())

                val arraySN = planta.scientific_name
                if (arraySN != null) {
                    for (text in arraySN) {
                        val textView = TextView(this)
                        textView.text = text
                        scientificNameLL.addView(textView)
                    }
                } else {
                    scientificNameLL.removeAllViews()
                }
            }
        }

        detailsViewModel.getPlantDetails(id.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}