import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.devright_stillbaaitourism.EatData
import com.squareup.picasso.Picasso
import com.example.devright_stillbaaitourism.R
import com.squareup.picasso.Cache

class CustomAdapter(private val context: Context, private val eatDataList: List<EatData>) : ArrayAdapter<EatData>(context, 0, eatDataList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val eatData = getItem(position)
        val imageView = itemView?.findViewById<ImageView>(R.id.imageView)
        val titleTextView = itemView?.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = itemView?.findViewById<TextView>(R.id.descriptionTextView)

        eatData?.EAT_IMAGE_URLS?.get(0)?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(imageView)
        }

        titleTextView?.text = eatData?.EAT_NAME ?: ""

        // Check if EAT_TEL_NUM is not null or empty, and display accordingly
        val phoneNumber: String = if (!eatData?.EAT_TEL_NUM.isNullOrBlank()) {
            eatData?.EAT_TEL_NUM ?: ""
        } else {
            eatData?.EAT_MOBILE_NUM ?: ""
        }

        // Set the phone number in the description field
        var descriptionText = phoneNumber

        // Check if EAT_WEBSITE is not null or empty, and add it to the description
        if (!eatData?.EAT_WEBSITE.isNullOrBlank()) {
            descriptionText += if (descriptionText.isNotBlank()) {
                "\n${eatData?.EAT_WEBSITE}"
            } else {
                eatData?.EAT_WEBSITE
            }
        }

        descriptionTextView?.text = descriptionText

        if (phoneNumber.isNotBlank()) {
            descriptionTextView?.setOnClickListener {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:$phoneNumber")
                context.startActivity(callIntent)
            }
        }

        if (eatData?.EAT_WEBSITE != null && Patterns.WEB_URL.matcher(eatData.EAT_WEBSITE).matches()) {
            descriptionTextView?.setOnClickListener {
                val websiteIntent = Intent(Intent.ACTION_VIEW, Uri.parse(eatData.EAT_WEBSITE))
                context.startActivity(websiteIntent)
            }
        }

        return itemView ?: View(context)
    }

}
