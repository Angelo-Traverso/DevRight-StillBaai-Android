package com.example.devright_stillbaaitourism

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.firebase.messaging.Constants.TAG
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), DataFetchCallback {

    private lateinit var burgerMenu: BurgerMenu

    @SuppressLint("CutPasteId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_main)
        burgerMenu.setupDrawer()

        val stilBaaiInfo = findViewById<TextView>(R.id.btnStil)
        val jongens = findViewById<TextView>(R.id.btnJong)
        val melk = findViewById<TextView>(R.id.btnMelk)

        stilBaaiInfo.setOnClickListener{
            intentForAbout()
        }

        jongens.setOnClickListener{
            intentForAbout()
        }

        melk.setOnClickListener{
            intentForAbout()
        }

        // Subscribe for notifications
        FirebaseMessaging.getInstance().subscribeToTopic("Notification")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
            }


        if(GlobalClass.EventDataList.isEmpty()) {
            val dbHandler = DBHandler(this)
            GlobalClass.StayDataList.clear()
            thread {
                dbHandler.getConnection()
                dbHandler.fetchActivityData()
                dbHandler.fetchContactData()
                dbHandler.fetchEatData()
                dbHandler.fetchBusinessData()
                dbHandler.fetchStayData()
                dbHandler.fetchListingData()
                dbHandler.fetchEventsData()
                dbHandler.fetchEelData()
            }

            dbHandler.getConnection()
        }else
        {
            populateEvents()
        }
        val advertFragment = listOf(
            AdvertFragment.newInstance("Come visit us today!", R.drawable.home_store, "https://stilbaaitourism.co.za/"),
            AdvertFragment.newInstance("Come feed the eels!", R.drawable.home_eel, "https://stilbaaitourism.co.za/stilbaai-eels/")
        )

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        // Create the FragmentPagerAdapter and set it to the ViewPager
        val adapter = EventPagerAdapter(supportFragmentManager, advertFragment)
        viewPager.adapter = adapter

        // Connect the TabLayout with the ViewPager for navigation
        tabLayout.setupWithViewPager(viewPager)
        val viewPagerr: ViewPager = findViewById(R.id.viewPager)

        val handler = Handler()

        // Set the initial delay and duration
        val initialDelay = 4000L  // 4 seconds delay before auto-scroll starts
        val scrollDuration = 6000L // 6 seconds duration for each scroll

        // Start auto-scrolling when the activity is created
        handler.postDelayed(object : Runnable {
            override fun run() {
                // Get the current item index
                var currentItem = viewPagerr.currentItem

                // Calculate the next item index (circular scrolling)
                currentItem = (currentItem + 1) % adapter.getCount()

                // Scroll to the next item with smooth scroll
                viewPagerr.setCurrentItem(currentItem, true)

                // Schedule the next auto-scroll after the specified duration
                handler.postDelayed(this, scrollDuration)
            }
        }, initialDelay)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDataFetched() {
        // This method will be called when data is fetched
        runOnUiThread {
            // Update UI or populate views here
            populateEvents()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun populateEvents() {
        val linearLayoutEvents = findViewById<LinearLayout>(R.id.linearEventsDisplay)

        val defaultImageResource = R.drawable.no_image

        // Fetching Event List
        val eventList = GlobalClass.EventDataList

        val sortedEventList = eventList.sortedBy { it.parseDate()?.atStartOfDay() }

        val upcomingEvents = sortedEventList.take(3)

        for (event in upcomingEvents) {

                val eventView = layoutInflater.inflate(R.layout.events_home, null)

                eventView.findViewById<TextView>(R.id.eventName).text = event.EVENT_NAME ?: ""
                eventView.findViewById<TextView>(R.id.eventTime).text = event.EVENT_STARTTIME ?: ""
                eventView.findViewById<TextView>(R.id.eventLocation).text =
                    event.EVENT_ADDRESS ?: ""
                val imageDisplay = eventView.findViewById<ImageView>(R.id.imgEvent)
                val btnGoToEvent = eventView.findViewById<MaterialButton>(R.id.btnGoToEvent)

                btnGoToEvent.setOnClickListener {
                    val intent = Intent(this@MainActivity, EventDetail::class.java)
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

                // Check if imageUrls is not null and load images into slideModels
                if (event.EVENT_IMAGE_URLS.isNotEmpty()) {
                    val imageUrl = event.EVENT_IMAGE_URLS[0]
                    Picasso.get().load(imageUrl).into(imageDisplay)
                } else {
                    imageDisplay?.setImageResource(defaultImageResource)
                }

                linearLayoutEvents.addView(eventView)

            /// Set event image based on whether there are events today
        }
        val imageView = ImageView(this)
        if (upcomingEvents.isEmpty()) {
            imageView.setImageResource(R.drawable.img_no_events)
            imageView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                650 // replace this with the desired height in pixels for the image when there are no events today
            )
            linearLayoutEvents.addView(imageView)
        } /*else {
            imageView.setImageResource(R.drawable.img_event_end)
            imageView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                250 // replace this with the desired height in pixels for the image when there are events today
            )
            linearLayoutEvents.addView(imageView)
        }*/

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun EventData.parseDate(): LocalDate? {
        val formats = arrayOf(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss")
            // Add more date formats as needed
        )

        for (format in formats) {
            try {
                return LocalDate.parse(this.EVENT_DATE, format)
            } catch (e: DateTimeParseException) {
                // Continue to the next format if parsing fails
            }
        }

        return null
    }

    fun intentForAbout()
    {
        val url = "https://stilbaaitourism.co.za/"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)

    }
}