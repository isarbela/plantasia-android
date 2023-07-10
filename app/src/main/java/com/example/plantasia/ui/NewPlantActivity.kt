package com.example.plantasia.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.plantasia.R
import com.example.plantasia.repository.Plant
import java.util.ArrayList


class NewPlantActivity : AppCompatActivity() {

    private lateinit var editNameView: EditText
    private lateinit var editAgeView: EditText
    private var plantList: ArrayList<Plant> = ArrayList<Plant>()

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_plant)

        editNameView = findViewById(R.id.name_edittext)
        editAgeView = findViewById(R.id.age_edittext)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val speciesArray = mutableListOf<String>()
        speciesArray.add(getString(R.string.spinner_placeholder))

        mainViewModel.plantsList.observe(this) { plants ->
            plants.let {
                plants?.forEach { plant ->
                    speciesArray.add(plant.common_name!!)
                    plantList.add(plant)
                }
            }
        }

        mainViewModel.getPlantsList()

        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            speciesArray
        )

        val spinner: Spinner = findViewById(R.id.species_spinner)
        spinner.adapter = adapter

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            val errorPlant = findViewById<CardView>(R.id.errorAddPlantCV)
            val idApi = spinner.selectedItemId - 1
            if (TextUtils.isEmpty(editNameView.text) || TextUtils.isEmpty(editAgeView.text) || (idApi < 1)){
                errorPlant.visibility = View.VISIBLE
            } else {
                errorPlant.visibility = View.INVISIBLE
                val name = editNameView.text.toString()
                val age = editAgeView.text.toString().toInt()
                val commonName = spinner.selectedItem.toString()
                val plant = fillPlant(name, age, commonName, idApi)
                replyIntent.putExtra(EXTRA_REPLY, plant)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }

    }

    private fun fillPlant(name: String, age: Int, commonName: String, idApi: Long): Plant {
        val plantApi = plantList[idApi.toInt()]
        return Plant(
            name = name,
            age = age,
            common_name = commonName,
            scientific_name = plantApi.scientific_name,
            cycle = plantApi.cycle,
            watering = plantApi.watering,
            indoor = plantApi.indoor,
            care_level = plantApi.care_level
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.plantlistsql.REPLY"
    }
}

