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

class EelFeeding : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eel_feeding)
        burgerMenu = BurgerMenu(this, R.layout.eel_feeding)
        burgerMenu.setupDrawer()

        val eelData = GlobalClass.EelDataList

        val imagesDisplay = findViewById<ImageSlider>(R.id.eelImageSlider)

        val title = findViewById<TextView>(R.id.tvEelTitle)
        val tvEelAddress = findViewById<TextView>(R.id.tvEelAddress)
        val tvEelContact = findViewById<TextView>(R.id.tvEelContact)
        val tvDescription = findViewById<TextView>(R.id.descriptionTextView)



        val eelName = eelData[0].EEL_NAME
        val eelContact = eelData[0].EEL_CONTACT_NUM
        val eelAddress = eelData[0].EEL_ADDRESS
        val eelDescription = eelData[0].EEL_DESCRIPTION
        val imageUrls = eelData[0].EEL_IMAGE_URLS


        title.text = eelName

        if(eelContact.isNotEmpty())
        {
            tvEelContact.text = eelContact
            tvEelContact.visibility = View.VISIBLE
        }

        if(eelAddress.isNotEmpty())
        {
            tvEelAddress.text = eelAddress
            tvEelAddress.visibility = View.VISIBLE
        }

        if(eelDescription.isNotEmpty())
        {
            tvDescription.text = eelDescription
            tvDescription.visibility = View.VISIBLE
        }

        tvEelAddress.setOnClickListener {

            // Create a Uri for the location (use the "q" parameter)
            val uri = Uri.parse("geo:0,0?q=$eelAddress")

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


        // Create a list of SlideModel to hold image data
        val slideModels = ArrayList<SlideModel>()

        // Check if imageUrls is not null and load images into slideModels
        if (imageUrls.isNotEmpty()) {
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