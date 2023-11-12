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
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.devright_stillbaaitourism.databinding.ActivityStayBinding
import com.google.android.material.navigation.NavigationView

class Stay : AppCompatActivity() {

    private lateinit var binding: ActivityStayBinding
    private lateinit var listView: ListView
    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stay)
        burgerMenu = BurgerMenu(this, R.layout.activity_stay)
        burgerMenu.setupDrawer()

        listView = findViewById(R.id.stayListView)

        val stayDataList = GlobalClass.StayDataList



    }

    //............................................................................................//

}