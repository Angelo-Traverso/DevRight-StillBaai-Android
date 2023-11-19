package com.example.devright_stillbaaitourism

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class BusinessAdapter (private val context: Context, private var businessData: List<BusinessData>) : BaseAdapter() {

    override fun getCount(): Int {
        return businessData.size
    }

    override fun getItem(position: Int): BusinessData {
        return businessData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Implement the view holder pattern to efficiently populate the view
        // Use your provided layout for each list item and set data from activityData
        val view =
            LayoutInflater.from(context).inflate(R.layout.business_item_layout, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val tvTitle = view.findViewById<TextView>(R.id.titleTextView)
        val tvCatName = view.findViewById<TextView>(R.id.tvBusinessCategory)

        val data = getItem(position)

        tvTitle.text = data.BUSINESS_NAME
        tvCatName.text = data.BUSINESS_CATEGORY_TYPE

        // Load the first image from ACTIVITY_IMAGE_URLS (assuming the list is not empty)

        if (data.BUSINESS_IMAGE_URLS.isNotEmpty()) {
            val firstImageUrl = data.BUSINESS_IMAGE_URLS[0]
            Picasso.get().load(firstImageUrl).into(imageView)
        }else
        {
            imageView.setImageResource(R.drawable.no_image)
        }

        return view
    }

    fun updateData(newList: List<BusinessData>) {
        businessData = newList.toMutableList()
        notifyDataSetChanged()
    }
}