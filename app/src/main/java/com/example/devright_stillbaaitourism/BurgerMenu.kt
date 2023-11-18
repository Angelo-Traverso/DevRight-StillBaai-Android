package com.example.devright_stillbaaitourism

import android.content.Intent
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class BurgerMenu(private val activity: AppCompatActivity, private val layoutResId: Int) : NavigationView.OnNavigationItemSelectedListener {

    private lateinit var menuBtn: ImageButton
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    fun setupDrawer() {
        activity.setContentView(layoutResId)

        menuBtn = activity.findViewById(R.id.btnMenu)
        drawerLayout = activity.findViewById(R.id.drawerLayout)
        navView = activity.findViewById(R.id.nav_view)

        // Open drawer on menu button clicked
        menuBtn.setOnClickListener {
            drawerLayout.open()
        }

        navView.bringToFront()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> navigateTo(MainActivity::class.java)
            R.id.nav_stay -> navigateTo(Stay::class.java)
            R.id.nav_eat -> navigateTo(Eat::class.java)
            R.id.nav_business -> navigateTo(Businesses::class.java)
            R.id.nav_Activities -> navigateTo(Activities::class.java)
            R.id.nav_Events -> navigateTo(Events::class.java)
            R.id.nav_eelFeeding -> navigateTo(EelFeeding::class.java)
            R.id.nav_Contacts -> navigateTo(ContactUs::class.java)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun navigateTo(destination: Class<*>) {
        val intent = Intent(activity.applicationContext, destination)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
    }

    fun onBackPressed(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }
}