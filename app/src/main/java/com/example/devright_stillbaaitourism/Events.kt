package com.example.devright_stillbaaitourism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devright_stillbaaitourism.databinding.ActivityEventsBinding
import com.google.android.material.navigation.NavigationView
// Create a data class to represent an Event
data class Event(
    val eventName: String,
    val eventAddress: String,
    val imageResourceId: Int
)


class Events : AppCompatActivity() {

    private lateinit var binding: ActivityEventsBinding
    private lateinit var burgerMenu: BurgerMenu

  //  private val eventList = GlobalClass.EventDataList

        private val eventList: List<Event> = listOf(
            Event("Surfing", "Quary Road, 24", R.drawable.eel_event_image),
            Event("Kite Flying", "Durbanville east, south", R.drawable.eel_event_image),
            Event("Eel Feedings", "Tracktor Street, plettenberg south", R.drawable.eel_event_image),
        )

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            burgerMenu = BurgerMenu(this, R.layout.activity_events)
            burgerMenu.setupDrawer()

            val linearLayout: LinearLayout = findViewById(R.id.linearEventsListings)

            // Loop through your event list and inflate the layout for each event
            for (event in eventList) {
                val eventView = layoutInflater.inflate(R.layout.events_custom_layout, null)

                // Set data for each event (assuming you have setter methods in your layout)
                val eventNameTextView: TextView = eventView.findViewById(R.id.eventNameTextView)
                val locationTextView: TextView = eventView.findViewById(R.id.locationTextView)
                val eventImageView: ImageView = eventView.findViewById(R.id.eventImageView)
                val addToCalendarButton: Button = eventView.findViewById(R.id.addToCalendarButton)

                eventNameTextView.text = event.eventName
                locationTextView.text = event.eventAddress
                eventImageView.setImageResource(event.imageResourceId)

                // Set click listener for "Add to Calendar" button
                addToCalendarButton.setOnClickListener {
                    val startTimeMillis = System.currentTimeMillis()
                    val endTimeMillis = startTimeMillis + 2 * 60 * 60 * 1000 // 2 hours

                    val intent = Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, event.eventName)
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, event.eventAddress)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTimeMillis)
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTimeMillis)
                        .putExtra(CalendarContract.Events.ALL_DAY, false)
                        .putExtra(CalendarContract.Events.DESCRIPTION, "Description of the event")

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