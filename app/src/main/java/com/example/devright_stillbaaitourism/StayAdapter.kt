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
 * Custom adapter for displaying StayData in a ListView.
 *
 * @param context The context in which the adapter is being used.
 * @param stayData The list of StayData to be displayed.
 */
class StayAdapter(private val context: Context, private var stayData: List<StayData>) : BaseAdapter() {

    /**
     * Returns the number of items in the adapter.
     *
     * @return The number of items in the adapter.
     */
    override fun getCount(): Int {
        return stayData.size
    }

    /**
     * Returns the StayData item at the specified position.
     *
     * @param position The position of the item to retrieve.
     * @return The StayData item at the specified position.
     */
    override fun getItem(position: Int): StayData {
        return stayData[position]
    }

    /**
     * Returns the unique identifier for the item at the specified position.
     *
     * @param position The position of the item.
     * @return The unique identifier for the item at the specified position.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Creates and returns a view for the specified position in the adapter.
     * Implements the view holder pattern to efficiently populate the view.
     *
     * @param position The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A view corresponding to the data at the specified position.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
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
        }else {
            imageView?.setImageResource(R.drawable.no_image)
        }

        return view
    }

    /**
     * Updates the data set of the adapter with a new list of StayData.
     *
     * @param newList The new list of StayData to be displayed.
     */
    fun updateData(newList: List<StayData>) {
        stayData = newList.toMutableList()
        notifyDataSetChanged()
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //