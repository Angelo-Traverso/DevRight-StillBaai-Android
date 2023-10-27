import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.devright_stillbaaitourism.EatData
import com.squareup.picasso.Picasso
import com.example.devright_stillbaaitourism.R

class CustomAdapter(private val context: Context, private val eatDataList: List<EatData>) : ArrayAdapter<EatData>(context, 0, eatDataList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        // Initializing views (list_item)
        val eatData = getItem(position)
        val imageView = itemView?.findViewById<ImageView>(R.id.imageView)
        val titleTextView = itemView?.findViewById<TextView>(R.id.titleTextView)
        val websiteUrlTextView = itemView?.findViewById<TextView>(R.id.tvWebsite)
        val contactTextView = itemView?.findViewById<TextView>(R.id.tvContactNum)


        // Loading images into imageView
        eatData?.EAT_IMAGE_URLS?.get(0)?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(imageView)
        }

        // Settings title text
        titleTextView?.text = eatData?.EAT_NAME ?: ""

        // Setting website visibility dependant on if one exists or not
        if(!eatData?.EAT_WEBSITE.isNullOrBlank()){
            websiteUrlTextView?.visibility = View.VISIBLE;
            websiteUrlTextView?.text = eatData?.EAT_WEBSITE
        }else{
            websiteUrlTextView?.visibility = View.GONE;
        }

        // Displaying a mobile or tell number, whichever exists
        if(!eatData?.EAT_MOBILE_NUM.isNullOrBlank()){
            contactTextView?.text = eatData?.EAT_MOBILE_NUM
        }else if(!eatData?.EAT_TEL_NUM.isNullOrBlank()){
            contactTextView?.text = eatData?.EAT_TEL_NUM
        }else{
            contactTextView?.visibility = View.GONE;
        }

        return itemView ?: View(context)
    }

}
