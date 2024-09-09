package com.example.happyplaces

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.happyplaces.models.HappyPlaceModel
import com.example.happyplaces.data.DatabaseHelper

class EditHappyPlaceActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editTextLocation: EditText
    private lateinit var buttonSave: Button
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var happyPlace: HappyPlaceModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_happy_place)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDescription = findViewById(R.id.editTextDescription)
        editTextDate = findViewById(R.id.editTextDate)
        editTextLocation = findViewById(R.id.editTextLocation)
        buttonSave = findViewById(R.id.buttonSave)

        dbHelper = DatabaseHelper(this)

        happyPlace = intent.getParcelableExtra("happyPlace")!!

        editTextTitle.setText(happyPlace.title)
        editTextDescription.setText(happyPlace.description)
        editTextDate.setText(happyPlace.date)
        editTextLocation.setText(happyPlace.location)

        buttonSave.setOnClickListener {
            saveChanges()
        }
    }

    private fun saveChanges() {
        val updatedPlace = HappyPlaceModel(
            happyPlace.id,
            editTextTitle.text.toString(),
            happyPlace.image,
            editTextDescription.text.toString(),
            editTextDate.text.toString(),
            editTextLocation.text.toString()
        )

        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, updatedPlace.title)
            put(DatabaseHelper.COLUMN_DESCRIPTION, updatedPlace.description)
            put(DatabaseHelper.COLUMN_DATE, updatedPlace.date)
            put(DatabaseHelper.COLUMN_LOCATION, updatedPlace.location)
        }

        val whereClause = "${DatabaseHelper.COLUMN_ID} = ?"
        val whereArgs = arrayOf(updatedPlace.id.toString())

        val rowsAffected = db.update(DatabaseHelper.TABLE_NAME, values, whereClause, whereArgs)
        if (rowsAffected > 0) {
            setResult(RESULT_OK)
            finish()
        }
    }
}
