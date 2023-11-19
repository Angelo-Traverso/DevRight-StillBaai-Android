package com.example.devright_stillbaaitourism

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ActivityAdapter(private val context: Context, private var activityData: List<ActivityData>) : BaseAdapter() {

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
        val tvCatName = view.findViewById<TextView>(R.id.tvCategoryName)

        val data = getItem(position)

        // Set data to the views
        tvActivityName.text = data.ACTIVITY_NAME
        tvCatName.text = data.ACTIVITY_CATEGORY_TYPE

        val defaultImageResource = R.drawable.no_image

        // Load the first image from ACTIVITY_IMAGE_URLS (assuming the list is not empty)
        if (data.ACTIVITY_IMAGE_URLS.isNotEmpty()) {
            val firstImageUrl = data.ACTIVITY_IMAGE_URLS[0]
            Picasso.get().load(firstImageUrl).into(imageView)
        }else{
            imageView.setImageResource(defaultImageResource)
        }

        return view
    }

    fun updateData(newList: List<ActivityData>) {
        activityData = newList.toMutableList()
        notifyDataSetChanged()
    }
}