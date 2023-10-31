package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_main)
        burgerMenu.setupDrawer()

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        // Create event fragments for each event
        val eventFragments = listOf(
            EventFragment.newInstance("Eel Feeding", "18:00", "Backyard, 2440"),
            EventFragment.newInstance("Dua Lipa", "19:00", "Open Field, 2440"),
        )

        // Create the FragmentPagerAdapter and set it to the ViewPager
        val adapter = EventPagerAdapter(supportFragmentManager, eventFragments)
        viewPager.adapter = adapter

        // Connect the TabLayout with the ViewPager for navigation
        tabLayout.setupWithViewPager(viewPager)

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