package com.example.happyplaces

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.happyplaces.models.HappyPlaceModel
import com.example.happyplaces.data.DatabaseHelper
import com.example.happyplaces.data.HappyPlaceAdapter

class HappyPlacesListActivity : AppCompatActivity() {

    private lateinit var listViewHappyPlaces: ListView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: HappyPlaceAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happy_places_list)

        val toolbar: Toolbar = findViewById(R.id.toolbar_happy_places_list)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbHelper = DatabaseHelper(this)
        listViewHappyPlaces = findViewById(R.id.listViewHappyPlaces)

        adapter = HappyPlaceAdapter(
            this,
            mutableListOf(),
            { happyPlace ->
                val intent = Intent(this, EditHappyPlaceActivity::class.java)
                intent.putExtra("happyPlace", happyPlace)
                startActivityForResult(intent, REQUEST_CODE_EDIT)
            },
            { happyPlace ->
                Toast.makeText(this, "Deleted: ${happyPlace.title}", Toast.LENGTH_SHORT).show()
                loadHappyPlaces()
            }
        )

        listViewHappyPlaces.adapter = adapter
        loadHappyPlaces()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadHappyPlaces() {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            null, null, null, null, null, null
        )

        val places = mutableListOf<HappyPlaceModel>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE))
                val location = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION))
                val image = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE))

                places.add(HappyPlaceModel(id, title, image, description, date, location))
            }
            close()
        }

        adapter.updateHappyPlaces(places)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            loadHappyPlaces()
        }
    }

    companion object {
        private const val REQUEST_CODE_EDIT = 1
    }
}
