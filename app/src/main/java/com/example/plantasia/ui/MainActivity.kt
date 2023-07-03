package com.example.plantasia.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantasia.R as plantasiaR


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(plantasiaR.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(plantasiaR.id.recyclerview)
        val adapter = PlantListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val emptyText = findViewById<TextView>(android.R.id.empty)
        if (recyclerView.isEmpty()) {
            emptyText.visibility = View.VISIBLE
        } else {
            emptyText.visibility = View.GONE
        }

        val btAddPlant = findViewById<View>(plantasiaR.id.button_add_plant) as Button
        btAddPlant.setOnClickListener(onClickBtAddPlantListener)
    }

    private val onClickBtAddPlantListener: View.OnClickListener = View.OnClickListener {
        val i = Intent(this@MainActivity, NewPlantActivity::class.java)
        startActivity(i)
    }

}


//    private fun isNetworkAvailable(): Boolean {
//        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        return networkCapabilities != null
//    }
