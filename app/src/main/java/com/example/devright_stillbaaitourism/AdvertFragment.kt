package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class AdvertFragment: Fragment() {
    companion object {
        // This companion object is used to create new instances of the fragment
        fun newInstance(advertTitle: String,imageUrl: String): AdvertFragment {
            val fragment = AdvertFragment()
            val args = Bundle()
            args.putString("advertTitle", advertTitle)
            args.putString("imageUrl", imageUrl)
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
        val advertTitle  = view.findViewById<TextView>(R.id.tvAdvertTitle)
        val saveEventButton = view.findViewById<Button>(R.id.btnGoToEvent)


        // Get event-specific data from arguments
        val args = arguments
        if (args != null) {
            val title = args.getString("advertTitle", "")
            val imageUrl = args.getString("imageUrl", "")

            advertTitle.text = title
            if (imageUrl.isNotEmpty()) {
                Picasso.get().load(imageUrl).into(eventImage)
            }
        }

        //eventImage.setImageResource(R.drawable.eel_event_image)

        saveEventButton.setOnClickListener {
            // Implement your save event logic here
        }

        return view
    }
}