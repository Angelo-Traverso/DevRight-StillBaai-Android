package com.example.devright_stillbaaitourism

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu
    private val stilBaaiUrl: String = "https://stilbaaitourism.co.za/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_main)
        burgerMenu.setupDrawer()

        val stilBaaiClick = findViewById<TextView>(R.id.btnStil)
        val jongensClick = findViewById<TextView>(R.id.btnJong)
        val melkClick = findViewById<TextView>(R.id.btnMelk)

        stilBaaiClick.setOnClickListener{
            val uri = Uri.parse(stilBaaiUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        jongensClick.setOnClickListener{
            val uri = Uri.parse(stilBaaiUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        melkClick.setOnClickListener{
            val uri = Uri.parse(stilBaaiUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val linearLayoutEvents = findViewById<LinearLayout>(R.id.linearEventsDisplay)


        // Create event fragments for each event
        val eventFragments = listOf(
            EventFragment.newInstance("Eel Feeding", "18:00", "Backyard, 2440"),
            EventFragment.newInstance("Dua Lipa", "19:00", "Open Field, 2440"),
            EventFragment.newInstance("Mamma Mia", "15:00", "Open Field, 2440"),
        )

        val advertFragment = listOf(
            AdvertFragment.newInstance("Come visit our store!", "https://fabricatecapetown.co.za/wp-content/uploads/2020/11/Fabricate_November-2019_006b-11-scaled.jpg"),
            AdvertFragment.newInstance("Come visit our store!", "https://fabricatecapetown.co.za/wp-content/uploads/2020/11/Fabricate_November-2019_006b-11-scaled.jpg")

        )

        // Create the FragmentPagerAdapter and set it to the ViewPager
        val adapter = EventPagerAdapter(supportFragmentManager, advertFragment)
        viewPager.adapter = adapter

        // Connect the TabLayout with the ViewPager for navigation
        tabLayout.setupWithViewPager(viewPager)

        for (eventFragment in eventFragments) {
            val eventView = layoutInflater.inflate(R.layout.events_home, null)

            eventView.findViewById<TextView>(R.id.eventName).text = "Test Event Name"
            eventView.findViewById<TextView>(R.id.eventTime).text = "19:00"
            eventView.findViewById<TextView>(R.id.eventLocation).text = "Down Town"

            linearLayoutEvents.addView(eventView)
        }

        val dbHandler = DBHandler();
        thread {
            dbHandler.getConnection()
            dbHandler.fetchActivityData()
            dbHandler.fetchContactData()
            dbHandler.fetchEatData()
            dbHandler.fetchStayData()
            dbHandler.fetchListingData()
        }

        dbHandler.getConnection();


        // ------------------- End Test ------------------- //


    }
}