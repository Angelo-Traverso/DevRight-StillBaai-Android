package com.example.devright_stillbaaitourism

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import kotlin.collections.ArrayList

class Events : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu
    private lateinit var eventList: List<EventData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_events)
        burgerMenu.setupDrawer()

        eventList = GlobalClass.EventDataList
        val linearLayout: LinearLayout = findViewById(R.id.linearEventsListings)
        var currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // Sort the eventList based on the event dates
        val sortedEventList = eventList.sortedBy { it.EVENT_DATE }

        val uniqueDates = sortedEventList.map { it.EVENT_DATE!! }.distinct()

        val todayDateString = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // Loop through the sorted event list
        for (currentDate in uniqueDates) {
            // Add a heading for the new date dynamically
            if (currentDate >= todayDateString) {
                val dateHeadingTextView = TextView(this)
                dateHeadingTextView.text = formatDate(currentDate)
                dateHeadingTextView.textSize = 20f
                dateHeadingTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
                dateHeadingTextView.setTypeface(null, Typeface.BOLD)
                dateHeadingTextView.setPadding(20, 20, 0, 8)
                linearLayout.addView(dateHeadingTextView)
            }

            // Iterate through the events with the current date and display them
            for (eventForDate in sortedEventList.filter { it.EVENT_DATE!! == currentDate }) {
                val eventView = layoutInflater.inflate(R.layout.events_custom_layout, null)

                // Set data for each event (assuming you have setter methods in your layout)
                if (eventForDate.EVENT_DATE!! >= todayDateString) {
                    val eventNameTextView: TextView = eventView.findViewById(R.id.eventNameTextView)
                    val timeTextView: TextView = eventView.findViewById(R.id.timeTextView)
                    val locationTextView: TextView = eventView.findViewById(R.id.tvEventLocation)
                    val eventImageView: ImageView = eventView.findViewById(R.id.eventImageView)
                    val addToCalendarButton: TextView =
                        eventView.findViewById(R.id.addToCalendarButton)
                    val cardViewEvent = eventView.findViewById<CardView>(R.id.cardEvent)

                    eventNameTextView.text = eventForDate.EVENT_NAME

                    if (!eventForDate.EVENT_ADDRESS.isNullOrEmpty()) {
                        locationTextView.visibility = View.VISIBLE
                        locationTextView.text = eventForDate.EVENT_ADDRESS
                    }

                    val defaultImageResource = R.drawable.no_image

                    // Loading images into imageView
                    if (eventForDate.EVENT_IMAGE_URLS.isNotEmpty()) {
                        val imageUrl = eventForDate.EVENT_IMAGE_URLS[0]
                        Picasso.get().load(imageUrl).into(eventImageView)
                    } else {
                        eventImageView.setImageResource(defaultImageResource)
                    }

                    timeTextView.text = eventForDate.EVENT_STARTTIME

                    // Set click listener for "Add to Calendar" button
                    addToCalendarButton.setOnClickListener {
                        val startTimeMillis = System.currentTimeMillis()
                        val endTimeMillis = startTimeMillis + 2 * 60 * 60 * 1000

                        val intent = Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, eventForDate.EVENT_NAME)
                            .putExtra(
                                CalendarContract.Events.EVENT_LOCATION,
                                eventForDate.EVENT_ADDRESS
                            )
                            .putExtra(
                                CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                                eventForDate.EVENT_STARTTIME
                            )
                            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTimeMillis)
                            .putExtra(CalendarContract.Events.ALL_DAY, false)
                            .putExtra(CalendarContract.Events.DESCRIPTION, "Reminder")

                        startActivity(intent)
                    }

                    cardViewEvent.setOnClickListener {
                        val intent = Intent(this@Events, EventDetail::class.java)

                        val imageUrls = ArrayList(eventForDate.EVENT_IMAGE_URLS)

                        intent.putExtra("eventName", eventForDate.EVENT_NAME)
                        intent.putExtra("eventNum", eventForDate.EVENT_NUM ?: "")
                        intent.putExtra("eventEmail", eventForDate.EVENT_EMAIL ?: "")
                        intent.putExtra("eventWebsite", eventForDate.EVENT_WEBSITE ?: "")
                        intent.putExtra("eventAddress", eventForDate.EVENT_ADDRESS)
                        intent.putExtra("eventPerson", eventForDate.EVENT_PERSON ?: "")
                        intent.putExtra("eventDate", eventForDate.EVENT_DATE ?: "")
                        intent.putExtra("eventStartTime", eventForDate.EVENT_STARTTIME ?: "")
                        intent.putExtra("eventDuration", eventForDate.EVENT_DURATION ?: "")
                        intent.putExtra("eventDescription", eventForDate.EVENT_DESCRIPTION ?: "")
                        intent.putStringArrayListExtra("eventImageUrls", imageUrls)

                        startActivity(intent)
                    }

                    locationTextView.setOnClickListener {
                        GlobalClass.openGoogleMaps(this, locationTextView.text.toString())
                    }

                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    layoutParams.setMargins(0, 7, 0, 16)
                    eventView.layoutParams = layoutParams
                    linearLayout.addView(eventView)
                }
            }
        }
    }
}

/**
 * Formats the input date string into a human-readable date format.
 *
 * @param inputDate The date string to be formatted (in "yyyy-MM-dd" format).
 * @return A formatted date string in the "EEEE, d MMMM" format (e.g., "Tuesday, 5 January").
 */
private fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = inputFormat.parse(inputDate)

    // Format the date in the desired output format
    val outputFormat = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
    return outputFormat.format(date ?: Date())
}


// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //