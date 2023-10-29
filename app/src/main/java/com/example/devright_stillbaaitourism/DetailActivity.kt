package com.example.devright_stillbaaitourism;
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.devright_stillbaaitourism.R // Replace with the correct import for your resources
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Binding back button
        val btnBack = findViewById<FloatingActionButton>(R.id.btnBack)

        // On Click listener for back button
        btnBack.setOnClickListener{
            onBackPressed()
        }

        // Get Extras from EAT
        val imageUrl = intent.getStringExtra("imageUrl")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val websiteURL = intent.getStringExtra("WebsiteURL")
        val contactNumber = intent.getStringExtra("ContactNumber")

        // Binding views
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.imageSlider)
        val websiteURLDisplay = findViewById<TextView>(R.id.tvWebsiteURL)
        val contactNumberDisplay = findViewById<TextView>(R.id.tvContactNumber)


        // Setting view values
        titleTextView.text = Html.fromHtml("<b>$title</b>")
        descriptionTextView.text = description

        websiteURLDisplay.text = websiteURL
        contactNumberDisplay.text = contactNumber

        // SlideModel
        val slideModels = ArrayList<SlideModel>()

        // Adds the image to the slide model
        slideModels.add(SlideModel(imageUrl, ScaleTypes.FIT))

        // Bind the list to the Image Slider
        imagesDisplay.setImageList(slideModels, ScaleTypes.FIT)



    }
}
