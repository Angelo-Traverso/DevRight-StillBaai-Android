package com.example.devright_stillbaaitourism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devright_stillbaaitourism.databinding.ActivityStayBinding
import com.google.android.material.navigation.NavigationView

class Stay : AppCompatActivity() {

    private lateinit var binding: ActivityStayBinding

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stay)
        burgerMenu = BurgerMenu(this, R.layout.activity_stay)
        burgerMenu.setupDrawer()


        /*val menuBtn = findViewById<ImageButton>(R.id.btnMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // Open drawer on menu button clicked
        menuBtn.setOnClickListener(){
            drawerLayout.open()
        }*/

        ///--------------------------------------------------------------------///

        // Temporary card display
       // val linearLayout = findViewById<LinearLayout>(R.id.linearStayListings);
        /*val listView = findViewById<ListView>(R.id.listStayDisplay);

        //linearLayout.removeAllViews()
      // listView.removeAllViews()

        for (i in 1..5)
        {
            val customCard = custom_card(this)

            listView.addView(customCard)

        }*/




    }

    //............................................................................................//

}