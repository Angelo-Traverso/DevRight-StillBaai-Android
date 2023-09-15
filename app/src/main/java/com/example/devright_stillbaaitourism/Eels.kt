package com.example.devright_stillbaaitourism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout

class Eels : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eels)

        val menuBtn = findViewById<ImageButton>(R.id.btnMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // Open drawer on menu button clicked
        menuBtn.setOnClickListener(){
            drawerLayout.open()
        }
    }
}