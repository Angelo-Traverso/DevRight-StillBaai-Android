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

        /**
         * Ensuring values are not null or empty before displaying.
         * If view is NOT null or empty, then the view will be set to visible and its values will
         * be displayed.
         */
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
            GlobalClass.openGoogleMaps(this, eelAddress)
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
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //