package com.example.devright_stillbaaitourism

import CustomAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devright_stillbaaitourism.databinding.ActivityEatBinding
import com.google.android.material.navigation.NavigationView

class Eat : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityEatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ----------------------- List View ----------------------- //
        val listView = findViewById<ListView>(R.id.listView)

        // List items
        val items = listOf(
            ListItem(R.drawable.thumbail, "Pizza World", "Fun in the sun with Pizza"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
        )

        val adapter = CustomAdapter(this, items)
        listView.adapter = adapter

        // Set item click listener for the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]

            // Create an Intent to open the DetailActivity
            val intent = Intent(this@Eat, DetailActivity::class.java)

            // Pass data to the DetailActivity using Intent extras
            intent.putExtra("title", selectedItem.title)
            intent.putExtra("description", selectedItem.description)
            intent.putExtra("thumbnailResId", selectedItem.thumbnailResId)

            startActivity(intent)
        }
        // ----------------------- END List View ----------------------- //

        val menuBtn = findViewById<ImageButton>(R.id.btnMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // Open drawer on menu button clicked
        menuBtn.setOnClickListener(){
            drawerLayout.open()
        }

        binding.navView.bringToFront()
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation item clicks here
        when(item.itemId) {
            // Handle menu item clicks here
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        // Handle back button press when the drawer is open
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        /*TODO: Handle other clicks if needed*/
    }
}
