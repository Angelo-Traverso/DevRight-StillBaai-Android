package com.example.devright_stillbaaitourism;

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
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

        /**
         * On Click for listView items. Intents user to detail view, passing all data needed
         */
        btnBack.setOnClickListener {
            onBackPressed()
        }

        /**
         * Getting values from Eats, values used to display detailed view
         */
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val websiteURL = intent.getStringExtra("WebsiteURL")
        val contactNumber = intent.getStringExtra("ContactNumber")
        val address = intent.getStringExtra("address")
        val email = intent.getStringExtra("email")
        val imageUrls = intent.getStringArrayListExtra("imageUrls")

        /**
         * Binding views
         */
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.imageSlider)
        val websiteURLDisplay = findViewById<TextView>(R.id.tvWebsiteURL)
        val tvEatEmail = findViewById<TextView>(R.id.tvEatEmail)
        val contactNumberDisplay = findViewById<TextView>(R.id.tvContactNumber)
        val addressLocation = findViewById<TextView>(R.id.tvLocationToEats)

        // Taking user to Google Maps navigation
        addressLocation.setOnClickListener {
            GlobalClass.openGoogleMaps(this, address.toString())
        }

        // Setting title
        titleTextView.text = Html.fromHtml("<b>$title</b>")

        // Setting description
        descriptionTextView.text = description

        /**
         * Ensuring values are not null or empty before displaying.
         * If view is NOT null or empty, then the view will be set to visible and its values will
         * be displayed.
         */
        if(!address.isNullOrEmpty())
        {
            addressLocation.text = address
            addressLocation.visibility = View.VISIBLE
        }

        // Checking if an email exists
        if(!email.isNullOrEmpty())
        {
            tvEatEmail.text = email
            tvEatEmail.visibility = View.VISIBLE
        }

        // Checking if a website exists
        if(!websiteURL.isNullOrEmpty())
        {
            websiteURLDisplay.text = websiteURL
            websiteURLDisplay.visibility = View.VISIBLE
        }

        // Checking if a contact number exists
        if(!contactNumber.isNullOrEmpty())
        {
            contactNumberDisplay.text = contactNumber
            contactNumberDisplay.visibility = View.VISIBLE
        }

        // Dynamically adding images to carousel
        val slideModels = ArrayList<SlideModel>()
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

    override fun attachBaseContext(newBase: Context) {
        val configuration = Configuration(newBase.resources.configuration)
        configuration.fontScale = 1f
        val context: Context = newBase.createConfigurationContext(configuration)
        super.attachBaseContext(context)
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //
