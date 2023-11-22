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
 * Custom adapter for the business data list.
 *
 * @param context The context of the activity or fragment.
 * @param businessData The list of business data to be displayed.
 */
class BusinessAdapter (private val context: Context, private var businessData: List<BusinessData>) : BaseAdapter() {

    /**
     * Gets the number of items in the business data list.
     *
     * @return The number of items.
     */
    override fun getCount(): Int {
        return businessData.size
    }

    /**
     * Gets the business data item at the specified position.
     *
     * @param position The position of the item.
     * @return The business data item.
     */
    override fun getItem(position: Int): BusinessData {
        return businessData[position]
    }

    /**
     * Gets the ID of the business data item at the specified position.
     *
     * @param position The position of the item.
     * @return The ID of the business data item.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Gets the view for a specific item in the business data list.
     *
     * @param position The position of the item.
     * @param convertView The recycled view to populate.
     * @param parent The parent view group.
     * @return The populated view for the business data item.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.business_item_layout, parent, false)

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

    /**
     * Updates the adapter with a new list of business data.
     *
     * @param newList The new list of business data.
     */
    fun updateData(newList: List<BusinessData>) {
        businessData = newList.toMutableList()
        notifyDataSetChanged()
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //