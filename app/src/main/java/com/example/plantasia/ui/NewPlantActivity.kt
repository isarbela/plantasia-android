package com.example.plantasia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plantasia.R

class NewPlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_plant)
    }

    fun onFinish() {
        this@NewPlantActivity.finish()
    }
}