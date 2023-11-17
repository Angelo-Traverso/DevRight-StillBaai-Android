package com.example.devright_stillbaaitourism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso

// Create a data class to represent an Event
/*data class Event(
    val eventName: String,
    val eventAddress: String,
    val imageResourceId: Int
)*/


class Events : AppCompatActivity() {

   /* private lateinit var customAdapter: CustomAdapter*/
    private lateinit var burgerMenu: BurgerMenu
    private lateinit var eventList: List<EventData>
  //  private val eventList = GlobalClass.EventDataList
/*
        private val eventList: List<Event> = listOf(
            Event("Surfing", "Quary Road, 24", R.drawable.eel_event_image),
            Event("Kite Flying", "Durbanville east, south", R.drawable.eel_event_image),
            Event("Eel Feedings", "Tracktor Street, plettenberg south", R.drawable.eel_event_image),
        )*/
        /*    var EVENT_ID: Int = 0
    var EVENT_NAME: String = ""
    var EVENT_NUM: String? = null
    var EVENT_EMAIL: String? = null
    var EVENT_WEBSITE: String? = null
    var EVENT_ADDRESS: String = ""
    var EVENT_PERSON: String? = null
    var EVENT_DATE: Date? = null
    var EVENT_STARTTIME: String? = null
    var EVENT_DURATION: Double? = null
    var EVENT_DESCRIPTION: String? = null*/
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            burgerMenu = BurgerMenu(this, R.layout.activity_events)
            burgerMenu.setupDrawer()

            eventList = GlobalClass.EventDataList

            val linearLayout: LinearLayout = findViewById(R.id.linearEventsListings)

            // Loop through your event list and inflate the layout for each event
            for (event in eventList) {
                val eventView = layoutInflater.inflate(R.layout.events_custom_layout, null)

                // Set data for each event (assuming you have setter methods in your layout)
                val eventNameTextView: TextView = eventView.findViewById(R.id.eventNameTextView)
                val timeTextView: TextView = eventView.findViewById(R.id.timeTextView)
                //val locationTextView: TextView = eventView.findViewById(R.id.locationTextView)
                val eventImageView: ImageView = eventView.findViewById(R.id.eventImageView)
                val addToCalendarButton: Button = eventView.findViewById(R.id.addToCalendarButton)

                eventNameTextView.text = event.EVENT_NAME

                val defaultImageResource = R.drawable.no_image

                // Loading images into imageView
                if (event.EVENT_IMAGE_URLS.isNotEmpty()) {
                    val imageUrl = event.EVENT_IMAGE_URLS[0]
                    Picasso.get().load(imageUrl).into(eventImageView)
                } else {
                    eventImageView.setImageResource(defaultImageResource)
                }

                timeTextView.text = event.EVENT_STARTTIME
                // Set click listener for "Add to Calendar" button
                addToCalendarButton.setOnClickListener {
                    val startTimeMillis = System.currentTimeMillis()
                    val endTimeMillis = startTimeMillis + 2 * 60 * 60 * 1000 // 2 hours

                    val intent = Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, event.EVENT_NAME)
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, event.EVENT_ADDRESS)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.EVENT_STARTTIME)
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTimeMillis)
                        .putExtra(CalendarContract.Events.ALL_DAY, false)
                        .putExtra(CalendarContract.Events.DESCRIPTION, "Reminder")

                    startActivity(intent)
                }

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                layoutParams.setMargins(0, 0, 0, 16)
                eventView.layoutParams = layoutParams
                // Add the inflated layout to your LinearLayout
                linearLayout.addView(eventView)
            }
        }
        //............................................................................................//
    }