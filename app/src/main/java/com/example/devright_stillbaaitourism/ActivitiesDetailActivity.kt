package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivitiesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activities)

        // Binding back button
        val btnBack = findViewById<FloatingActionButton>(R.id.btnBack)

        // On Click for back button click
        btnBack.setOnClickListener{
            onBackPressed()
        }

        // Retrieving data from Activities.kt
        val activityName = intent.getStringExtra("ActivityName")
        val description = intent.getStringExtra("Description")
        val imageUrls = intent.getStringArrayListExtra("imageUrls")
        val contactNumber = intent.getStringExtra("ContactNumber")
        val websiteLink = intent.getStringExtra("WebsiteLink")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")

        // Binding views
        val tvActName = findViewById<TextView>(R.id.tvActivityName)
        val tvDescription = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.imageSlider)
        val tvContactNumber = findViewById<TextView>(R.id.tvContactNumber)
        val tvWebsiteLink = findViewById<TextView>(R.id.tvWebsiteURL)
        val tvActEmail = findViewById<TextView>(R.id.tvEmail)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)



        tvActName.text = activityName
        tvDescription.text = Html.fromHtml(description)

        if(!contactNumber.isNullOrEmpty())
        {
            tvContactNumber.text = contactNumber
            tvContactNumber.visibility = View.VISIBLE
        }

        if(!websiteLink.isNullOrEmpty())
        {
            tvWebsiteLink.text = websiteLink
            tvWebsiteLink.visibility = View.VISIBLE
        }

        if(!email.isNullOrEmpty())
        {
            tvActEmail.text = email
            tvActEmail.visibility = View.VISIBLE
        }

        if(!address.isNullOrEmpty())
        {
            tvAddress.text = address
            tvAddress.visibility = View.VISIBLE
        }


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