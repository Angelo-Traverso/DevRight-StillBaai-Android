package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class EventFragment : Fragment() {
    companion object {
        // This companion object is used to create new instances of the fragment
        fun newInstance(eventName: String, eventTime: String, eventPlace: String): EventFragment {
            val fragment = EventFragment()
            val args = Bundle()
            args.putString("eventName", eventName)
            args.putString("eventTime", eventTime)
            args.putString("eventPlace", eventPlace)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.events_home, container, false)

        val eventImage = view.findViewById<ImageView>(R.id.imgEvent)
        val eventName = view.findViewById<TextView>(R.id.eventName)
        val eventTime = view.findViewById<TextView>(R.id.eventTime)
        val eventPlace = view.findViewById<TextView>(R.id.eventLocation)
        val saveEventButton = view.findViewById<Button>(R.id.btnGoToEvent)

        // Get event-specific data from arguments
        val args = arguments
        if (args != null) {
            val name = args.getString("eventName", "")
            val time = args.getString("eventTime", "")
            val place = args.getString("eventPlace", "")

            eventName.text = name
            eventTime.text = time
            eventPlace.text = place
        }

        eventImage.setImageResource(R.drawable.eel_event_image)

        saveEventButton.setOnClickListener {
            // Implement your save event logic here
        }

        return view
    }
}