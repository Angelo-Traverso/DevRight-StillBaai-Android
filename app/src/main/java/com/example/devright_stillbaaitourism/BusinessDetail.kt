package com.example.devright_stillbaaitourism

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BusinessDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.business_detail)

        // Binding back button
        val btnBack = findViewById<FloatingActionButton>(R.id.btnBack)

        // On Click for back button click
        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Retrieving data from Activities.kt
        val businessName = intent.getStringExtra("BusinessName")
        val description = intent.getStringExtra("Description")
        val imageUrls = intent.getStringArrayListExtra("imageUrls")
        val businessEmail = intent.getStringExtra("mail")
        val businessWebsite = intent.getStringExtra("Website")
        val address = intent.getStringExtra("address")
        val contactNumber = intent.getStringExtra("ContactNumber")

        // Binding views
        val tvTitle = findViewById<TextView>(R.id.titleTextViewBusiness)
        val tvDescription = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.businessImageSlider)
        val tvMail = findViewById<TextView>(R.id.tvEmail)
        val tvWebsite = findViewById<TextView>(R.id.tvWebsite)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        val tvContactNumber = findViewById<TextView>(R.id.tvContactNumberBusiness)

        // Taking user to Google Maps navigation
        tvAddress.setOnClickListener {

            // Create a Uri for the location (use the "q" parameter)
            val uri = Uri.parse("geo:0,0?q=$address")

            // Create an Intent to view the location on Google Maps
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)

            // Set the package to specify that you want to use Google Maps
            mapIntent.setPackage("com.google.android.apps.maps")

            // Check if there's an app available to handle the intent
            if (mapIntent.resolveActivity(packageManager) != null) {
                // Start the intent to open Google Maps
                startActivity(mapIntent)

            } else {
                Toast.makeText(this, "Google Maps is not installed.", Toast.LENGTH_SHORT).show()
            }
        }

        if(!businessEmail.isNullOrEmpty())
        {
            tvMail.text = businessEmail
            tvMail.visibility = View.VISIBLE
        }

        if(!businessWebsite.isNullOrEmpty())
        {
            tvWebsite.text = businessWebsite
            tvWebsite.visibility = View.VISIBLE
        }

        if(!address.isNullOrEmpty())
        {
            tvAddress.text = address
            tvAddress.visibility = View.VISIBLE
        }

        if(!contactNumber.isNullOrEmpty())
        {
            tvContactNumber.text = contactNumber
            tvContactNumber.visibility = View.VISIBLE
        }

        tvTitle.text = businessName
        tvDescription.text = description


        // Create a list of SlideModel to hold image data
        val slideModels = ArrayList<SlideModel>()

        // Check if imageUrls is not null and load images into slideModels
        if (!imageUrls.isNullOrEmpty()) {
            for (imageUrl in imageUrls) {
                slideModels.add(SlideModel(imageUrl))
            }
            // Bind the list to the Image Slider
            imagesDisplay.setImageList(slideModels, ScaleTypes.FIT)
        }else
        {
            slideModels.add(SlideModel(R.drawable.no_image))
            imagesDisplay.setImageList(slideModels, ScaleTypes.FIT)
        }
    }
}