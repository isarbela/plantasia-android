package com.example.plantasia.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantasia.PlantasiaApplication
import com.example.plantasia.repository.Plant
import com.example.plantasia.R as plantasiaR


class MainActivity : AppCompatActivity() {
    private val newPlantActivityRequestCode = 1

    private val plantViewModel: PlantViewModel by viewModels {
        PlantViewModelFactory((application as PlantasiaApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(plantasiaR.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(plantasiaR.id.recyclerview)
        val adapter = PlantListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        plantViewModel.allPlants.observe( this) {
            plants -> plants.let { adapter.submitList(it) }
        }

        val btAddPlant = findViewById<View>(plantasiaR.id.button_add_plant) as Button
        btAddPlant.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPlantActivity::class.java)
            startActivityForResult(intent, newPlantActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newPlantActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra(NewPlantActivity.EXTRA_REPLY)
            val age = data?.getIntExtra(NewPlantActivity.EXTRA_REPLY_AGE, 0)
            if (age != null && name != null) {
                val plant = Plant(name=name, age = age)
                plantViewModel.insert(plant)
            }
        } else {
            Toast.makeText(
                applicationContext,
                plantasiaR.string.empty_list,
                Toast.LENGTH_LONG
            ).show()
        }
    }

}


//    private fun isNetworkAvailable(): Boolean {
//        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        return networkCapabilities != null
//    }
