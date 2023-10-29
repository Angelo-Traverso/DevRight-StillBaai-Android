package com.example.devright_stillbaaitourism

import CustomAdapter
import android.content.Intent
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
import com.example.devright_stillbaaitourism.databinding.ActivityActivitiesBinding

import com.google.android.material.navigation.NavigationView

class Activities : AppCompatActivity() {

    private lateinit var binding: ActivityActivitiesBinding

    private lateinit var activitiesAdapter: ActivityAdapter

    private lateinit var listView: ListView

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_activities)
        burgerMenu.setupDrawer()



         listView = findViewById(R.id.activityListView)

        // Instance of EatDataList
        val activityDataList = GlobalClass.ActivityDataList

        activitiesAdapter = ActivityAdapter(this, activityDataList)

        listView.adapter = activitiesAdapter

        // Item click listener for the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < activityDataList.size) {
                val selectedItem = activityDataList[position]

                // Creating an Intent to open the DetailActivity
                val intent = Intent(this@Activities, ActivitiesDetailActivity::class.java)
                val imageUrls = ArrayList(selectedItem.ACTIVITY_IMAGE_URLS)
                // Passing data to the DetailActivity using Intent extras
                intent.putExtra("ActivityName", selectedItem.ACTIVITY_NAME)
                intent.putExtra("Description", selectedItem.ACTIVITY_DESCRIPTION)

                intent.putExtra("WebsiteLink", selectedItem.ACTIVITY_WEBSITE ?: "")

                //intent.putExtra("Address", selectedItem.ACTIVITY_ADDRESS)
                intent.putStringArrayListExtra("imageUrls", imageUrls)

                // Use either mobile number or tell number, whichever is available
               val contactNumber: String = if (!selectedItem.ACTIVITY_MOBILE_NUM.isNullOrBlank()){

                    selectedItem.ACTIVITY_MOBILE_NUM?:""
                }else
                {
                    selectedItem.ACTIVITY_TEL_NUM?:""
                }

                intent.putExtra("ContactNumber", contactNumber)

                startActivity(intent)
            }
        }
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
        /*val linearLayout = findViewById<LinearLayout>(R.id.linearActivitiesListings);
        linearLayout.removeAllViews()

        for (i in 1..5)
        {
            val customCard = custom_card(this)

            linearLayout.addView(customCard)

        }*/

    }

    //............................................................................................//
}