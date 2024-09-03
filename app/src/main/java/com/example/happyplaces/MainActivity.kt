package com.example.happyplaces

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.happyplaces.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHomePage)

        binding?.fabAddHappyPlace?.setOnClickListener {
            Log.d("MainActivity", "FAB clicked")
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }

    }
}
