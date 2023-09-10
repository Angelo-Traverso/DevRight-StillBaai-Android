import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.devright_stillbaaitourism.ListItem
import com.example.devright_stillbaaitourism.R

class CustomAdapter(private val context: Context, private val items: List<ListItem>) : ArrayAdapter<ListItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val item = getItem(position)

        val imageView = itemView?.findViewById<ImageView>(R.id.imageView)
        val titleTextView = itemView?.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = itemView?.findViewById<TextView>(R.id.descriptionTextView)

        imageView?.setImageResource(item?.thumbnailResId ?: 0)
        titleTextView?.text = item?.title
        descriptionTextView?.text = item?.description

        return itemView ?: View(context)
    }
}
