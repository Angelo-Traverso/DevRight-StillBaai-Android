package com.example.devright_stillbaaitourism;
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.devright_stillbaaitourism.R // Replace with the correct import for your resources

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail) // Replace with your layout resource file

        // Retrieve data passed from the main activity
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val thumbnailResId = intent.getIntExtra("thumbnailResId", 0)

        // Display the data in the detail view
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val imageView = findViewById<ImageView>(R.id.imageView)

        titleTextView.text = title
        descriptionTextView.text = description
        imageView.setImageResource(thumbnailResId)
    }
}
