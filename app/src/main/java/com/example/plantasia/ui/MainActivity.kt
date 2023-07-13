package com.example.plantasia.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantasia.PlantasiaApplication
import com.example.plantasia.R
import com.example.plantasia.repository.Plant


class MainActivity : AppCompatActivity() {
    private val newPlantActivityRequestCode = 1

    private val plantViewModel: PlantViewModel by viewModels {
        PlantViewModelFactory((application as PlantasiaApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PlantListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        plantViewModel.allPlants.observe( this) {
            plants -> plants.let { adapter.submitList(it) }
        }

        val btAddPlant = findViewById<View>(R.id.button_add_plant) as Button
        btAddPlant.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPlantActivity::class.java)
            startActivityForResult(intent, newPlantActivityRequestCode)
        }

        adapter.onItemClick = {
            val intent = Intent(this, PlantDetailsActivity::class.java)
            intent.putExtra("plant", it.id)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newPlantActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val planta = data?.getSerializableExtra(NewPlantActivity.EXTRA_REPLY)
            plantViewModel.insert(planta as Plant)
        } else if (resultCode != Activity.RESULT_CANCELED) {
            Toast.makeText(
                applicationContext,
                R.string.error_add_plant,
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
