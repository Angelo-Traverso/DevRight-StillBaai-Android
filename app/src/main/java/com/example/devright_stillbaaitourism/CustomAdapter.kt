import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.example.devright_stillbaaitourism.R
import com.squareup.picasso.Cache

class CustomAdapter(private val context: Context, private val imageUrls: List<String>) : ArrayAdapter<String>(context, 0, imageUrls) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            // Inflate the layout for each list item if it's not already created
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        // Get the URL for the current image
        val imageUrl = getItem(position)

        // Find the ImageView, and TextViews in the list item layout
        val imageView = itemView?.findViewById<ImageView>(R.id.imageView)
        val titleTextView = itemView?.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = itemView?.findViewById<TextView>(R.id.descriptionTextView)

        // Load the image using Picasso from the "rendered" URL
        Picasso.get().load(imageUrl).into(imageView)

        // Set other TextViews as needed - Should make dynamic
        // Set the title text
        titleTextView?.text = "Title"
        // Set the description text
        descriptionTextView?.text = "Description"

        return itemView ?: View(context)
    }

}
