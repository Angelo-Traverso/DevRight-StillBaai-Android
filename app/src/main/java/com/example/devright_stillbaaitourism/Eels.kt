package com.example.devright_stillbaaitourism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout

class Eels : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_eels)
        burgerMenu.setupDrawer()

        /*val menuBtn = findViewById<ImageButton>(R.id.btnMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // Open drawer on menu button clicked
        menuBtn.setOnClickListener(){
            drawerLayout.open()
        }*/
    }
}