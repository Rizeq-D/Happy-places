package com.example.happyplaces

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.happyplaces.databinding.ActivityAddHappyPlaceBinding


class AddHappyPlaceActivity : AppCompatActivity() {

    private  var binding: ActivityAddHappyPlaceBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddHappyPlaceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarAddPlace)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarAddPlace?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}