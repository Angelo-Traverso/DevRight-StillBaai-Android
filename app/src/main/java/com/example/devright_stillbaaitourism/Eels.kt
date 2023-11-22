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

    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //