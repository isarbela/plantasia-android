package com.example.plantasia.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.plantasia.R

class NewPlantActivity : AppCompatActivity() {

    private lateinit var editNameView: EditText
    private lateinit var editAgeView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_plant)

        editNameView = findViewById(R.id.name_edittext)
        editAgeView = findViewById(R.id.age_edittext)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text) || TextUtils.isEmpty(editAgeView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editNameView.text.toString()
                val age = editAgeView.text.toString().toInt()
                replyIntent.putExtra(EXTRA_REPLY, name)
                replyIntent.putExtra(EXTRA_REPLY_AGE, age)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    fun onFinish() {
        this@NewPlantActivity.finish()
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.plantlistsql.REPLY"
        const val EXTRA_REPLY_AGE = "com.example.android.plantlistsql.REPLY_AGE"
    }
}

