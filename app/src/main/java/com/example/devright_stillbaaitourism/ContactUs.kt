package com.example.devright_stillbaaitourism

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class ContactUs : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_contact_us)
        burgerMenu.setupDrawer()

        val container = findViewById<LinearLayout>(R.id.linearContactUs)

        for (contactData in GlobalClass.ContactDataList) {
            // Inflate a custom view for each ContactData item
            val customView = layoutInflater.inflate(R.layout.contact_layout, container, false)

            // Find the TextViews in the custom view
            val titleTextView = customView.findViewById<TextView>(R.id.tvContactName)
            val contactNumberTextView = customView.findViewById<TextView>(R.id.tvContact)
            val emailTextView = customView.findViewById<TextView>(R.id.tvEmail)
            val contactAddress = customView.findViewById<TextView>(R.id.tvLocation)

            // Taking user to Google Maps navigation
            contactAddress.setOnClickListener {

                // Create a Uri for the location (use the "q" parameter)
                val uri = Uri.parse("geo:0,0?q=${contactData.CONTACT_ADDRESS}")

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

            // Populate the TextViews with data from ContactData
            titleTextView.text = contactData.CONTACT_NAME
            contactNumberTextView.text = contactData.CONTACT_NUM

            // Check if email and address exist before displaying
            if (!contactData.CONTACT_EMAIL.isNullOrBlank()) {
                emailTextView.text = contactData.CONTACT_EMAIL
            } else {
                emailTextView.visibility = View.GONE // Hide the TextView
            }

            if (!contactData.CONTACT_ADDRESS.isNullOrBlank()) {
                contactAddress.text = contactData.CONTACT_ADDRESS
            } else {
                contactAddress.visibility = View.GONE // Hide the TextView
            }

            // Add the custom view to the container
            container.addView(customView)
        }


    }
}