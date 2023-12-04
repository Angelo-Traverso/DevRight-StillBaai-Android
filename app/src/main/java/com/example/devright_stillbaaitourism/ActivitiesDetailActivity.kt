package com.example.devright_stillbaaitourism

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivitiesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activities)

        // Back button binding
        val btnBack = findViewById<FloatingActionButton>(R.id.btnBack)

        /**
         * On Click for listView items. Intents user to detail view, passing all data needed
         */
        btnBack.setOnClickListener{
            onBackPressed()
        }

        /**
         * Getting values from activities, values used to display detailed view
         */
        val activityName = intent.getStringExtra("ActivityName")
        val description = intent.getStringExtra("Description")
        val imageUrls = intent.getStringArrayListExtra("imageUrls")
        val contactNumber = intent.getStringExtra("ContactNumber")
        val websiteLink = intent.getStringExtra("WebsiteLink")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")

        /**
         * Binding views
         */
        val tvActName = findViewById<TextView>(R.id.tvActivityName)
        val tvDescription = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.imageSlider)
        val tvContactNumber = findViewById<TextView>(R.id.tvContactNumber)
        val tvWebsiteLink = findViewById<TextView>(R.id.tvWebsiteURL)
        val tvActEmail = findViewById<TextView>(R.id.tvEmail)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)

        // Setting views that cant be null
        tvActName.text = activityName
        tvDescription.text = Html.fromHtml(description)

        /**
         * Ensuring values are not null or empty before displaying.
         * If view is NOT null or empty, then the view will be set to visible and its values will
         * be displayed.
         */
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


        /**
         * Creating a list of slideModels to be displayed in the ImageSlider
         */
        val slideModels = ArrayList<SlideModel>()

        // Check if imageUrls is not null and load images into slideModels
        if (!imageUrls.isNullOrEmpty()) {

            for (imageUrl in imageUrls) {
                slideModels.add(SlideModel(imageUrl))
            }

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