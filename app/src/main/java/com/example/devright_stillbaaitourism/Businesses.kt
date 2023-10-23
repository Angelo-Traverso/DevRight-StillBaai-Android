package com.example.devright_stillbaaitourism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devright_stillbaaitourism.databinding.ActivityBusinessesBinding
import com.google.android.material.navigation.NavigationView

class Businesses : AppCompatActivity() {

    private lateinit var binding: ActivityBusinessesBinding

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_businesses)
        burgerMenu.setupDrawer()


        /*val menuBtn = findViewById<ImageButton>(R.id.btnMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // Open drawer on menu button clicked
        menuBtn.setOnClickListener(){
            drawerLayout.open()
        }*/
        ///--------------------------------------------------------------------//

        /*binding.navView.bringToFront()
        binding.navView.setNavigationItemSelectedListener(this)*/

        ///--------------------------------------------------------------------///

        // Temporary card display
        /*val linearLayout = findViewById<LinearLayout>(R.id.linearBusinessesListings);
        linearLayout.removeAllViews()

        for (i in 1..5)
        {
            val customCard = custom_card(this)

            linearLayout.addView(customCard)

        }*/

    }

    //............................................................................................//
}