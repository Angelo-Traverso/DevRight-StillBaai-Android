package com.example.devright_stillbaaitourism

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Custom adapter for the list of EatData.
 *
 * @param context The context of the activity or fragment.
 * @param eatDataList The list of EatData to be displayed.
 */
class CustomAdapter (private val context: Context, private var eatDataList: List<EatData>) : BaseAdapter() {

    /**
     * Gets the number of items in the EatData list.
     *
     * @return The number of items.
     */
    override fun getCount(): Int {
        return eatDataList.size
    }

    /**
     * Gets the EatData item at the specified position.
     *
     * @param position The position of the item.
     * @return The EatData item.
     */
    override fun getItem(position: Int): EatData {
        return eatDataList[position]
    }

    /**
     * Gets the ID of the EatData item at the specified position.
     *
     * @param position The position of the item.
     * @return The ID of the EatData item.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Gets the ID of the EatData item at the specified position.
     *
     * @param position The position of the item.
     * @return The ID of the EatData item.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        // Initializing views (list_item)
        val eatData = getItem(position)
        val imageView = itemView?.findViewById<ImageView>(R.id.imageView)
        val titleTextView = itemView?.findViewById<TextView>(R.id.titleTextView)
        val contactTextView = itemView?.findViewById<TextView>(R.id.tvContactNum)
        val locationToEats = itemView?.findViewById<TextView>(R.id.tvLocationToEats)
        val defaultImageResource = R.drawable.no_image

        // Loading images into imageView
        if (eatData.EAT_IMAGE_URLS.isNotEmpty()) {
            val imageUrl = eatData.EAT_IMAGE_URLS[0]
            Picasso.get().load(imageUrl).into(imageView)
        } else {
            imageView?.setImageResource(defaultImageResource)
        }

        // Settings title text
        titleTextView?.text = eatData.EAT_NAME

        // Setting location
        locationToEats?.text = eatData.EAT_ADDRESS

        // Displaying a mobile or tell number, whichever exists
        if(!eatData.EAT_MOBILE_NUM.isNullOrBlank()){
            contactTextView?.text = eatData.EAT_MOBILE_NUM
        }else if(!eatData.EAT_TEL_NUM.isNullOrBlank()){
            contactTextView?.text = eatData.EAT_TEL_NUM
        }else{
            contactTextView?.visibility = View.GONE;
        }

        return itemView ?: View(context)
    }

    /**
     * Updates the adapter with a new list of EatData.
     *
     * @param newList The new list of EatData.
     */
    fun updateData(newList: List<EatData>) {
        eatDataList = newList.toMutableList()
        notifyDataSetChanged()
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //