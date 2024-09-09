package com.example.happyplaces.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.happyplaces.models.HappyPlaceModel

class HappyPlaceRepository(context: Context) {

    private val databaseHelper = DatabaseHelper(context)

    // Create
    fun addHappyPlace(happyPlace: HappyPlaceModel) {
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, happyPlace.title)
            put(DatabaseHelper.COLUMN_DESCRIPTION, happyPlace.description)
            put(DatabaseHelper.COLUMN_DATE, happyPlace.date)
            put(DatabaseHelper.COLUMN_LOCATION, happyPlace.location)
            put(DatabaseHelper.COLUMN_IMAGE, happyPlace.image)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values)
        db.close()
    }

    // Read
    fun getAllHappyPlaces(): List<HappyPlaceModel> {
        val happyPlaces = mutableListOf<HappyPlaceModel>()
        val db = databaseHelper.readableDatabase
        val cursor: Cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null)

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE))
                val location = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION))
                val image = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE))

                happyPlaces.add(HappyPlaceModel(id, title, description, date, location, image))
            }
            close()
        }
        return happyPlaces
    }

    fun getHappyPlaceById(id: Int): HappyPlaceModel? {
        val db = databaseHelper.readableDatabase
        val cursor: Cursor = db.query(DatabaseHelper.TABLE_NAME, null, "${DatabaseHelper.COLUMN_ID}=?", arrayOf(id.toString()), null, null, null)

        cursor.use {
            if (it.moveToFirst()) {
                val title = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE))
                val description = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION))
                val date = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE))
                val location = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION))
                val image = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE))

                return HappyPlaceModel(id, title, description, date, location, image)
            }
        }
        return null
    }

    // Update
    fun updateHappyPlace(happyPlace: HappyPlaceModel) {
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, happyPlace.title)
            put(DatabaseHelper.COLUMN_DESCRIPTION, happyPlace.description)
            put(DatabaseHelper.COLUMN_DATE, happyPlace.date)
            put(DatabaseHelper.COLUMN_LOCATION, happyPlace.location)
            put(DatabaseHelper.COLUMN_IMAGE, happyPlace.image)
        }
        db.update(DatabaseHelper.TABLE_NAME, values, "${DatabaseHelper.COLUMN_ID}=?", arrayOf(happyPlace.id.toString()))
        db.close()
    }

    // Delete
    fun deleteHappyPlace(id: Int) {
        val db = databaseHelper.writableDatabase
        db.delete(DatabaseHelper.TABLE_NAME, "${DatabaseHelper.COLUMN_ID}=?", arrayOf(id.toString()))
        db.close()
    }
}
