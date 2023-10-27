package com.example.devright_stillbaaitourism;
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.example.devright_stillbaaitourism.R // Replace with the correct import for your resources
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val imageUrl = intent.getStringExtra("imageUrl")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")


        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val imageView = findViewById<ImageView>(R.id.imageView)

        titleTextView.text = Html.fromHtml("<b>$title</b>")
        descriptionTextView.text = description

        // Load the image using Picasso
        Picasso.get().load(imageUrl).into(imageView)


    }
}
