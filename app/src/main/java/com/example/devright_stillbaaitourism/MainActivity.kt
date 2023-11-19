package com.example.devright_stillbaaitourism

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), DataFetchCallback {

    private lateinit var burgerMenu: BurgerMenu


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_main)
        burgerMenu.setupDrawer()

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
            AdvertFragment.newInstance("Come visit our store!", "https://fabricatecapetown.co.za/wp-content/uploads/2020/11/Fabricate_November-2019_006b-11-scaled.jpg"),
            AdvertFragment.newInstance("Come visit our store!", "https://fabricatecapetown.co.za/wp-content/uploads/2020/11/Fabricate_November-2019_006b-11-scaled.jpg")

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

        var eventcount: Int = 1

        // Track if there are any events today
        var eventsToday = false

        for (event in eventList) {
            if (event.isEventToday()) {
                eventsToday = true

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
            }

            /// Set event image based on whether there are events today

        }
        val imageView = ImageView(this)
        if (!eventsToday) {
            imageView.setImageResource(R.drawable.img_no_events)
            imageView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                650 // replace this with the desired height in pixels for the image when there are no events today
            )
            linearLayoutEvents.addView(imageView)
        } else {
            imageView.setImageResource(R.drawable.img_event_end)
            imageView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                250 // replace this with the desired height in pixels for the image when there are events today
            )
            linearLayoutEvents.addView(imageView)
        }

    }
}