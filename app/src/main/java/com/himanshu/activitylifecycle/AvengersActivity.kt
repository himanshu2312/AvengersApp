package com.himanshu.activitylifecycle

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AvengersActivity : AppCompatActivity() {
    private lateinit var btnSend: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avengers)

        sharedPreferences=getSharedPreferences(R.string.preference_file_name.toString(),
            MODE_PRIVATE)
        title=sharedPreferences.getString("title","AVENGERS")

        btnSend=findViewById(R.id.btnSend)

        btnSend.setOnClickListener{
            val intent=Intent(this@AvengersActivity,LoginActivity::class.java)
            sharedPreferences.edit().clear().apply()
            finish()
            startActivity(intent)
        }
    }

}