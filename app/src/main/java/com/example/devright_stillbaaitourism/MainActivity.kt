package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_main)
        burgerMenu.setupDrawer()

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