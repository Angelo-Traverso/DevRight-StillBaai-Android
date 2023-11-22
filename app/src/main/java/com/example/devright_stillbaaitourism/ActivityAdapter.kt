package com.example.devright_stillbaaitourism

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ActivityAdapter(private val context: Context, private var activityData: List<ActivityData>) : BaseAdapter() {

    /**
     * Returns the total number of items in the adapter.
     *
     * @return The total number of items.
     */
    override fun getCount(): Int {
        return activityData.size
    }

    /**
     * Returns the item at the specified position in the adapter.
     *
     * @param position The position of the item to retrieve.
     * @return The item at the specified position.
     */
    override fun getItem(position: Int): ActivityData {
        return activityData[position]
    }

    /**
     * Returns the stable item ID at the specified position.
     *
     * @param position The position of the item within the adapter's data set.
     * @return The stable ID of the item at the specified position.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.activities_listview_layout, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val tvActivityName = view.findViewById<TextView>(R.id.tvActivityName)
        val tvCatName = view.findViewById<TextView>(R.id.tvCategoryName)
        val defaultImageResource = R.drawable.no_image
        val data = getItem(position)

        // Set data to the views
        tvActivityName.text = data.ACTIVITY_NAME
        tvCatName.text = data.ACTIVITY_CATEGORY_TYPE

        // Load the first image from ACTIVITY_IMAGE_URLS (assuming the list is not empty)
        if (data.ACTIVITY_IMAGE_URLS.isNotEmpty()) {
            val firstImageUrl = data.ACTIVITY_IMAGE_URLS[0]
            Picasso.get().load(firstImageUrl).into(imageView)
        } else {
            imageView.setImageResource(defaultImageResource)
        }

        return view
    }

    /**
     * Update the adapter's data with a new list and notify any registered observers that the data set has changed.
     *
     * @param newList The new list of ActivityData to update the adapter with.
     */
    fun updateData(newList: List<ActivityData>) {
        activityData = newList.toMutableList()
        notifyDataSetChanged()
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //