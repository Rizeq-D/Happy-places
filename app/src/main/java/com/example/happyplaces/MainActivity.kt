package com.example.happyplaces

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar_home_page)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Happy Places"

        val fabAddHappyPlace: FloatingActionButton = findViewById(R.id.fabAddHappyPlace)
        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this@MainActivity, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }

        val btnViewHappyPlaces: Button = findViewById(R.id.btnViewHappyPlaces)
        btnViewHappyPlaces.setOnClickListener {
            val intent = Intent(this@MainActivity, HappyPlacesListActivity::class.java)
            startActivity(intent)
        }
    }
}
