package com.example.devright_stillbaaitourism

import android.opengl.Visibility
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StayDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stay_detail)

        // Binding back button
        val btnBack = findViewById<FloatingActionButton>(R.id.btnBack)

        // On Click for back button click
        btnBack.setOnClickListener{
            onBackPressed()
        }

        // Retrieving data from Activities.kt
        val stayName = intent.getStringExtra("StayName")
        val description = intent.getStringExtra("Description")
        val imageUrls = intent.getStringArrayListExtra("imageUrls")
        val websiteLink = intent.getStringExtra("WebsiteLink")
        val contactNumber = intent.getStringExtra("ContactNumber")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")


        // Binding views
        val tvActName = findViewById<TextView>(R.id.titleTextView)
        val tvDescription = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.imageSlider)
        val tvContactNumber = findViewById<TextView>(R.id.tvContactNumber)
        val tvWebsiteLink = findViewById<TextView>(R.id.tvWebsiteURL)
        val tvEmail = findViewById<TextView>(R.id.tvStayEmail)
        val tvAddress = findViewById<TextView>(R.id.tvStayLocation)

        tvActName.text = stayName
        tvDescription.text = Html.fromHtml(description)

        if(!websiteLink.isNullOrEmpty())
        {
            tvWebsiteLink.text = websiteLink
            tvWebsiteLink.visibility = View.VISIBLE
        }

        if(!contactNumber.isNullOrEmpty())
        {
            tvContactNumber.text = contactNumber
            tvContactNumber.visibility = View.VISIBLE
        }

        if(!email.isNullOrEmpty())
        {
            tvEmail.text = email
            tvEmail.visibility = View.VISIBLE
        }

        if(!address.isNullOrEmpty())
        {
            tvAddress.text = address
            tvAddress.visibility = View.VISIBLE
        }

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