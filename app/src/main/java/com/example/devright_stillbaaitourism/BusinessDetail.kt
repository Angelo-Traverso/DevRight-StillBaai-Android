package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.view.View
import android.widget.TextView
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

        /*   val contactNumber = intent.getStringExtra("ContactNumber")
           val websiteLink = intent.getStringExtra("WebsiteLink")*/

        // Binding views
        val tvTitle = findViewById<TextView>(R.id.titleTextViewBusiness)
        val tvDescription = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.businessImageSlider)
        val tvMail = findViewById<TextView>(R.id.tvEmail)
        val tvWebsite = findViewById<TextView>(R.id.tvWebsite)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)


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

        tvTitle.text = businessName
        tvDescription.text = description


        // Create a list of SlideModel to hold image data
        val slideModels = ArrayList<SlideModel>()

        // Check if imageUrls is not null and load images into slideModels
        if (imageUrls != null) {
            for (imageUrl in imageUrls) {
                slideModels.add(SlideModel(imageUrl))
            }
        }

        // Set the slideModels as the data source for ImageSlider
        imagesDisplay.setImageList(slideModels, ScaleTypes.FIT)
    }
}