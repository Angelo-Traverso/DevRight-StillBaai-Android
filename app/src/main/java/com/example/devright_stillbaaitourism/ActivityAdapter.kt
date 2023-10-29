package com.example.devright_stillbaaitourism

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ActivityAdapter(private val context: Context, private val activityData: List<ActivityData>) : BaseAdapter() {

    override fun getCount(): Int {
        return activityData.size
    }

    override fun getItem(position: Int): ActivityData {
        return activityData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Implement the view holder pattern to efficiently populate the view
        // Use your provided layout for each list item and set data from activityData
        val view =
            LayoutInflater.from(context).inflate(R.layout.activities_listview_layout, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val tvActivityName = view.findViewById<TextView>(R.id.tvActivityName)
        val tvWebsite = view.findViewById<TextView>(R.id.tvWebsite)
        val tvContactNum = view.findViewById<TextView>(R.id.tvContactNum)
        val tvCatName = view.findViewById<TextView>(R.id.tvCategoryName)

        val data = getItem(position)

        // Set data to the views
        tvActivityName.text = data.ACTIVITY_NAME
        tvWebsite.text = data.ACTIVITY_WEBSITE
        tvContactNum.text = data.ACTIVITY_TEL_NUM
        tvCatName.text = data.ACTIVITY_CATEGORY_TYPE

// Load the first image from ACTIVITY_IMAGE_URLS (assuming the list is not empty)
        if (data.ACTIVITY_IMAGE_URLS.isNotEmpty()) {
            val firstImageUrl = data.ACTIVITY_IMAGE_URLS[0]
            Picasso.get().load(firstImageUrl).into(imageView)
        }
        return view
    }
}