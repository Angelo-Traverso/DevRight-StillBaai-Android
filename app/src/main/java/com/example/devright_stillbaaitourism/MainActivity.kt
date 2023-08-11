package com.example.devright_stillbaaitourism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ------------------- Remove when implementing custom card ------------------- //
        // ------------------- This is used to test the display of the card layout ------------------- //
        val linearLayout = findViewById<LinearLayout>(R.id.linView);
        linearLayout.removeAllViews()

        for (i in 1..5)
        {
            val customCard = custom_card(this)

            linearLayout.addView(customCard)

        }

        // ------------------- End Test ------------------- //


    }
}