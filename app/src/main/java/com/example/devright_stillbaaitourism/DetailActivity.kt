package com.example.devright_stillbaaitourism;

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Binding back button
        val btnBack = findViewById<FloatingActionButton>(R.id.btnBack)

        // On Click listener for back button
        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Get Extras from EAT
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val websiteURL = intent.getStringExtra("WebsiteURL")
        val contactNumber = intent.getStringExtra("ContactNumber")
        val address = intent.getStringExtra("address")
        val email = intent.getStringExtra("email")
        val imageUrls = intent.getStringArrayListExtra("imageUrls")

        // Binding views
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.imageSlider)
        val websiteURLDisplay = findViewById<TextView>(R.id.tvWebsiteURL)
        val tvEatEmail = findViewById<TextView>(R.id.tvEatEmail)
        val contactNumberDisplay = findViewById<TextView>(R.id.tvContactNumber)
        val addressLocation = findViewById<TextView>(R.id.tvLocationToEats)

        addressLocation.setOnClickListener {

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
                // Handle the case where Google Maps is not installed
                // You can display a message to the user or use an alternative map application
                Toast.makeText(this, "Google Maps is not installed.", Toast.LENGTH_SHORT).show()
            }
        }


        // Setting view values
        titleTextView.text = Html.fromHtml("<b>$title</b>")

        descriptionTextView.text = description

        addressLocation.text = address

        if(!email.isNullOrEmpty())
        {
            tvEatEmail.text = email
            tvEatEmail.visibility = View.VISIBLE
        }

        if(!websiteURL.isNullOrEmpty())
        {
            websiteURLDisplay.text = websiteURL
            websiteURLDisplay.visibility = View.VISIBLE
        }

        if(!contactNumber.isNullOrEmpty())
        {
            contactNumberDisplay.text = contactNumber
            contactNumberDisplay.visibility = View.VISIBLE
        }

        // Dynamically adding images to carousel
        val slideModels = ArrayList<SlideModel>()
        if (imageUrls != null) {
            for (imageUrl in imageUrls) {
                slideModels.add(SlideModel(imageUrl))
            }
        }

        // Bind the list to the Image Slider
        imagesDisplay.setImageList(slideModels, ScaleTypes.FIT)


    }
}
