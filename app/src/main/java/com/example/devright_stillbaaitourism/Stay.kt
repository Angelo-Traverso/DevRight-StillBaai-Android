package com.example.devright_stillbaaitourism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.devright_stillbaaitourism.databinding.ActivityStayBinding
import com.google.android.material.navigation.NavigationView

class Stay : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var stayAdapter: StayAdapter
    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stay)
        burgerMenu = BurgerMenu(this, R.layout.activity_stay)
        burgerMenu.setupDrawer()

        listView = findViewById(R.id.stayListView)


        val stayDataList = GlobalClass.StayDataList


        stayAdapter = StayAdapter(this, stayDataList)

        listView.adapter = stayAdapter


        // Item click listener for the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < stayDataList.size) {
                val selectedItem = stayDataList[position]

                // Creating an Intent to open the DetailActivity
                val intent = Intent(this@Stay, ActivitiesDetailActivity::class.java)
                val imageUrls = ArrayList(selectedItem.STAY_IMAGE_URLS)
                // Passing data to the DetailActivity using Intent extras
                intent.putExtra("StayName", selectedItem.STAY_NAME)
                intent.putExtra("Description", selectedItem.STAY_DESCRIPTION)

          /*      intent.putExtra("WebsiteLink", selectedItem.ACTIVITY_WEBSITE ?: "")*/

                //intent.putExtra("Address", selectedItem.ACTIVITY_ADDRESS)
                intent.putStringArrayListExtra("imageUrls", imageUrls)

             /*   // Use either mobile number or tell number, whichever is available
                val contactNumber: String = if (!selectedItem.ACTIVITY_MOBILE_NUM.isNullOrBlank()){

                    selectedItem.ACTIVITY_MOBILE_NUM?:""
                }else
                {
                    selectedItem.ACTIVITY_TEL_NUM?:""
                }

                intent.putExtra("ContactNumber", contactNumber)
*/
                startActivity(intent)
            }
        }


    }

    //............................................................................................//

}