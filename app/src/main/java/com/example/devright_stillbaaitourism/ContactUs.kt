package com.example.devright_stillbaaitourism

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
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
                GlobalClass.openGoogleMaps(this, contactAddress.text.toString())
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

    override fun attachBaseContext(newBase: Context) {
        val configuration = Configuration(newBase.resources.configuration)
        configuration.fontScale = 1f
        val context: Context = newBase.createConfigurationContext(configuration)
        super.attachBaseContext(context)
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //