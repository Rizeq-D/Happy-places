package com.example.happyplaces.data

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.happyplaces.R
import com.example.happyplaces.models.HappyPlaceModel
import java.io.File

class HappyPlaceAdapter(
    private val context: Context,
    private val happyPlaces: MutableList<HappyPlaceModel>,
    private val onEditClick: (HappyPlaceModel) -> Unit,
    private val onDeleteClick: (HappyPlaceModel) -> Unit
) : BaseAdapter() {

    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    override fun getCount(): Int = happyPlaces.size

    override fun getItem(position: Int): Any = happyPlaces[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.happy_place_item, parent, false)
        val happyPlace = getItem(position) as HappyPlaceModel
        val imageViewHappyPlace = view.findViewById<ImageView>(R.id.image_View_Happy_Place)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewDescription = view.findViewById<TextView>(R.id.textViewDescription)
        val buttonEdit = view.findViewById<ImageButton>(R.id.buttonEdit)
        val buttonDelete = view.findViewById<ImageButton>(R.id.buttonDelete)

        Log.d("Image Path", "Path: ${happyPlace.image}")

        textViewTitle.text = happyPlace.title
        textViewDescription.text = happyPlace.description

        val uri = Uri.parse(happyPlace.image)
        val file = File(uri.path)
        if (file.exists()) {
            Glide.with(context)
                .load(file)
                .placeholder(R.drawable.add_screen_image_placeholder)
                .into(imageViewHappyPlace)
        } else {
            Log.e("Image Load", "File does not exist: $file")
        }

        buttonEdit.setOnClickListener {
            onEditClick(happyPlace)
        }

        buttonDelete.setOnClickListener {
            deleteHappyPlace(happyPlace)
            onDeleteClick(happyPlace)
        }

        return view
    }

    private fun deleteHappyPlace(happyPlace: HappyPlaceModel) {
        val db = dbHelper.writableDatabase
        val whereClause = "${DatabaseHelper.COLUMN_ID} = ?"
        val whereArgs = arrayOf(happyPlace.id.toString())

        val rowsAffected = db.delete(DatabaseHelper.TABLE_NAME, whereClause, whereArgs)
        if (rowsAffected > 0) {
            happyPlaces.remove(happyPlace)
            notifyDataSetChanged()
        }
    }

    fun updateHappyPlaces(newHappyPlaces: List<HappyPlaceModel>) {
        happyPlaces.clear()
        happyPlaces.addAll(newHappyPlaces)
        notifyDataSetChanged()
    }
}
