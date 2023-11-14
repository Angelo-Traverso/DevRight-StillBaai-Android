package com.example.devright_stillbaaitourism

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

// EVERYTHING IS SETUP, DATA NEEDS TO BE STORED PROPERLY

class StayAdapter(private val context: Context, private val stayData: List<StayData>) : BaseAdapter() {

    override fun getCount(): Int {
        return stayData.size
    }

    override fun getItem(position: Int): StayData {
        return stayData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Implement the view holder pattern to efficiently populate the view
        // Use your provided layout for each list item and set data from activityData
        val view = LayoutInflater.from(context).inflate(R.layout.stay_template, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val tvTitle = view.findViewById<TextView>(R.id.tvStayTitle)
        val tvCatName = view.findViewById<TextView>(R.id.tvStayCategory)

        val data = getItem(position)

        tvTitle.text = data.STAY_NAME
        tvCatName.text = data.STAY_CATEGORY_TYPE

        // Load the first image from ACTIVITY_IMAGE_URLS (assuming the list is not empty)

        if (data.STAY_IMAGE_URLS.isNotEmpty()) {
            val firstImageUrl = data.STAY_IMAGE_URLS[0]
            Picasso.get().load(firstImageUrl).into(imageView)
        }

        return view
    }
}