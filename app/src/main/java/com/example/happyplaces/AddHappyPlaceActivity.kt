package com.example.happyplaces

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.happyplaces.databinding.ActivityAddHappyPlaceBinding


class AddHappyPlaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddHappyPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding = ActivityAddHappyPlaceBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbarAddPlace)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarAddPlace.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}