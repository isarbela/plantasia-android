package com.example.plantasia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.plantasia.R
import com.example.plantasia.repository.Plant

class PlantDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val plant: Plant = intent.getSerializableExtra("plant") as Plant
        val textView: TextView = findViewById(R.id.plantNameTV)
        textView.text = plant.name
    }
}