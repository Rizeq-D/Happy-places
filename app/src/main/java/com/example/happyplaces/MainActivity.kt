package com.example.happyplaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbarHomePage = findViewById<Toolbar>(R.id.toolbar_home_page)
        setSupportActionBar(toolbarHomePage)
        supportActionBar?.title = "Happy Places"

        val fabAddHappyPlace = findViewById<FloatingActionButton>(R.id.fabAddHappyPlace)
        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this@MainActivity, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
    }
}
