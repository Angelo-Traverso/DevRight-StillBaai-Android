package com.example.devright_stillbaaitourism

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), DataFetchCallback {

    private lateinit var burgerMenu: BurgerMenu
    private val stilBaaiUrl: String = "https://stilbaaitourism.co.za/"


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

            dbHandler.getConnection();
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

        // Fetching Event List
        val eventList = GlobalClass.EventDataList

        var eventcount: Int = 1

        for (event in eventList) {

            val eventView = layoutInflater.inflate(R.layout.events_home, null)

            eventView.findViewById<TextView>(R.id.eventName).text = event.EVENT_NAME ?: ""
            eventView.findViewById<TextView>(R.id.eventTime).text = event.EVENT_STARTTIME ?: ""
            eventView.findViewById<TextView>(R.id.eventLocation).text = event.EVENT_ADDRESS ?: ""

            linearLayoutEvents.addView(eventView)

            if (event.isEventToday())
            {
                eventcount++
                val eventView = layoutInflater.inflate(R.layout.events_home, null)

                eventView.findViewById<TextView>(R.id.eventName).text = event.EVENT_NAME ?: ""
                eventView.findViewById<TextView>(R.id.eventTime).text = event.EVENT_STARTTIME ?: ""
                eventView.findViewById<TextView>(R.id.eventLocation).text = event.EVENT_ADDRESS ?: ""

                linearLayoutEvents.addView(eventView)

            }
        }

        //set event image
        val imageView = ImageView(this)

        if (eventcount == 0) {
            imageView.setImageResource(R.drawable.img_no_events)
            imageView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                650 // replace this with the desired height in pixels for the first image
            )
        } else {
            imageView.setImageResource(R.drawable.img_event_end)
            imageView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                250 // replace this with the desired height in pixels for the second image
            )
        }
        linearLayoutEvents.addView(imageView)

    }

}