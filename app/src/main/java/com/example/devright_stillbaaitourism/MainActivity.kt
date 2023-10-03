package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbHandler = DBHandler();

        // ------------------- Remove when implementing custom card ------------------- //
        // ------------------- This is used to test the display of the card layout ------------------- //
        val linearLayout = findViewById<LinearLayout>(R.id.linView);
        linearLayout.removeAllViews()

        for (i in 1..5)
        {
            val customCard = custom_card(this)

            linearLayout.addView(customCard)

        }

        dbHandler.getConnection();
        val eatDataList = dbHandler.fetchEatData()


        // ------------------- End Test ------------------- //


    }
}