package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EventDetail: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_detail)

        // Binding back button
        val btnBack = findViewById<FloatingActionButton>(R.id.btnBack)

        // On Click for back button click
        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Retrieving data from Activities.kt
        val imageDisplay = findViewById<ImageSlider>(R.id.eventImageSlider)
        val eventName = intent.getStringExtra("eventName")
        val eventNum = intent.getStringExtra("eventNum")
        val eventEmail = intent.getStringExtra("eventEmail")
        val eventWebsite = intent.getStringExtra("eventWebsite")
        val eventAddress = intent.getStringExtra("eventAddress")
        val eventPerson = intent.getStringExtra("eventPerson")
        val eventDate = intent.getStringExtra("eventDate")
        val eventStartTime = intent.getStringExtra("eventStartTime")
        val eventDuration = intent.getStringExtra("eventDuration")
        val eventDescription = intent.getStringExtra("eventDescription")
        val imageUrls = intent.getStringArrayListExtra("eventImageUrls")
        /*   val contactNumber = intent.getStringExtra("ContactNumber")
           val websiteLink = intent.getStringExtra("WebsiteLink")*/

        // Binding views
        val tvEventName = findViewById<TextView>(R.id.tvEventName)

        val tvEventNum = findViewById<TextView>(R.id.tvEventNum)

        val tvEventEmail = findViewById<TextView>(R.id.tvEmail)

        val tvWebsite = findViewById<TextView>(R.id.tvWebsite)

        val tvAddress = findViewById<TextView>(R.id.tvAddress)


        //val tvEventPerson = findViewById<TextView>(R.id.tvEventName)

        val tvEventDate = findViewById<TextView>(R.id.tvEventDate)

        val tvEventStartTime = findViewById<TextView>(R.id.tvStartTime)

        //val tvDuration = findViewById<TextView>(R.id.tvEventName)

        val tvEventDescription = findViewById<TextView>(R.id.descriptionTextView)

        tvEventName.text = eventName
        tvEventDescription.text = eventDescription

        if(!eventNum.isNullOrEmpty())
        {
            tvEventNum.text = eventNum
            tvEventNum.visibility = View.VISIBLE
        }

        if(!eventEmail.isNullOrEmpty())
        {
            tvEventEmail.text = eventEmail
            tvEventEmail.visibility = View.VISIBLE
        }

        if(!eventWebsite.isNullOrEmpty())
        {
            tvWebsite.text = eventWebsite
            tvWebsite.visibility = View.VISIBLE
        }

        if(!eventAddress.isNullOrEmpty())
        {
            tvAddress.text = eventAddress
            tvAddress.visibility = View.VISIBLE
        }

        if(!eventDate.isNullOrEmpty())
        {
            tvEventDate.text = eventDate
            tvEventDate.visibility = View.VISIBLE
        }

        if(!eventStartTime.isNullOrEmpty())
        {
            tvEventStartTime.text = eventStartTime
            tvEventStartTime.visibility = View.VISIBLE
        }

        // Create a list of SlideModel to hold image data
        val slideModels = ArrayList<SlideModel>()

        // Check if imageUrls is not null and load images into slideModels
        if (!imageUrls.isNullOrEmpty()) {
            for (imageUrl in imageUrls) {
                slideModels.add(SlideModel(imageUrl))
            }
            // Bind the list to the Image Slider
            imageDisplay.setImageList(slideModels, ScaleTypes.FIT)
        }else
        {
            slideModels.add(SlideModel(R.drawable.no_image))
            imageDisplay.setImageList(slideModels, ScaleTypes.FIT)
        }
    }
}