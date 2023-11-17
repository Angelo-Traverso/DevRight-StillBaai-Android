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

class Events : AppCompatActivity() {
    private lateinit var burgerMenu: BurgerMenu
    private lateinit var eventList: List<EventData>

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
                val viewEventDetails: Button = eventView.findViewById(R.id.btnViewEventDetails)

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

                viewEventDetails.setOnClickListener{
                    val intent = Intent(this@Events, EventDetail::class.java)

                    val imageUrls = ArrayList(event.EVENT_IMAGE_URLS)

                    intent.putExtra("eventName", event.EVENT_NAME)
                    intent.putExtra("eventNum", event.EVENT_NUM ?: "")
                    intent.putExtra("eventEmail", event.EVENT_EMAIL ?: "")
                    intent.putExtra("eventWebsite", event.EVENT_WEBSITE ?: "")
                    intent.putExtra("eventAddress", event.EVENT_ADDRESS)
                    intent.putExtra("eventPerson", event.EVENT_PERSON ?: "")
                    intent.putExtra("eventDate", event.EVENT_DATE ?: "")
                    intent.putExtra("eventStartTime", event.EVENT_STARTTIME ?: "")
                    intent.putExtra("eventDuration", event.EVENT_DURATION ?: "")
                    intent.putExtra("eventDescription", event.EVENT_DESCRIPTION ?: "")
                    intent.putStringArrayListExtra("eventImageUrls", imageUrls)

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