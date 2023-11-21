package com.example.devright_stillbaaitourism

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class AdvertFragment : Fragment() {
    companion object {
        // This companion object is used to create new instances of the fragment
        fun newInstance(advertTitle: String, imageDrawable: Int, websiteUrl: String): AdvertFragment {
            val fragment = AdvertFragment()
            val args = Bundle()
            args.putString("advertTitle", advertTitle)
            args.putInt("imageUrl", imageDrawable)
            args.putString("websiteUrl", websiteUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.custom_adverts, container, false)

        val eventImage = view.findViewById<ImageView>(R.id.imgEvent)
        val advertTitle = view.findViewById<TextView>(R.id.tvAdvertTitle)
        val saveEventButton = view.findViewById<Button>(R.id.btnGoToEvent)

        // Get event-specific data from arguments
        val args = arguments
        if (args != null) {
            val title = args.getString("advertTitle", "")
            val imageUrl = args.getInt("imageUrl", 0)
            val websiteUrl = args.getString("websiteUrl", "")

            advertTitle.text = title
            eventImage.setImageResource(imageUrl)

            saveEventButton.setOnClickListener {
                // Open the website URL when the button is clicked
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
                startActivity(intent)
            }
        }

        return view
    }
}
