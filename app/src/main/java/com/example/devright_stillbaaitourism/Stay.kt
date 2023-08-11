package com.example.devright_stillbaaitourism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.devright_stillbaaitourism.databinding.ActivityStayBinding
import com.google.android.material.navigation.NavigationView

class Stay : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityStayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///--------------------------------------------------------------------///
        /// Navigation bar
        setSupportActionBar(binding.navToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var toggleOnOff = ActionBarDrawerToggle(
            this,
            binding.drawerLayout, binding.navToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggleOnOff)
        toggleOnOff.syncState()

        binding.navView.bringToFront()
        binding.navView.setNavigationItemSelectedListener(this)

        ///--------------------------------------------------------------------///

        val linearLayout = findViewById<LinearLayout>(R.id.linearStayListings);
        linearLayout.removeAllViews()

        for (i in 1..5)
        {
            val customCard = custom_card(this)

            linearLayout.addView(customCard)

        }

    }

    //............................................................................................//

    /// It will allow the user to navigate through pages.
    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        // When the activity pages are ready, uncomment the below code
        when(item.itemId)
        {
            /*R.id.nav_home -> {
                val intent = Intent(applicationContext, Home::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }

            R.id.nav_stay -> {
                val intent = Intent(applicationContext, Stay::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }

            R.id.nav_eat -> {
                val intent = Intent(applicationContext, Eat::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }*/
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        // return true marks the item as selected
        return true
    }

    //............................................................................................//

    /// Opens/closses the navigation drawer.
    override fun onBackPressed()
    {
        //if the drawer is open, close it
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else
        {
            //otherwise, let the super class handle it
            super.onBackPressed()
        }
    }

    //............................................................................................//

    override fun onClick(v: View?) {
        /*TODO("Not yet implemented")*/
    }

    //............................................................................................//

}