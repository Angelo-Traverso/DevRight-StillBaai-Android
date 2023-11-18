package com.example.devright_stillbaaitourism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devright_stillbaaitourism.databinding.ActivityBusinessesBinding
import com.google.android.material.navigation.NavigationView

class Businesses : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var binding: ActivityBusinessesBinding
    private lateinit var businessAdapter: BusinessAdapter

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_businesses)
        burgerMenu.setupDrawer()

        listView = findViewById(R.id.businessListView)

        val edtSearch = findViewById<EditText>(R.id.etSearch)

        val businessDataList = GlobalClass.BusinessDataList

        businessAdapter = BusinessAdapter(this, businessDataList)

        listView.adapter = businessAdapter


        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < businessDataList.size) {
                val selectedItem = businessDataList[position]

                // Creating an Intent to open the DetailActivity
                val intent = Intent(this@Businesses, BusinessDetail::class.java)
                val imageUrls = ArrayList(selectedItem.BUSINESS_IMAGE_URLS)
                // Passing data to the DetailActivity using Intent extras
                intent.putExtra("BusinessName", selectedItem.BUSINESS_NAME)
                intent.putExtra("Description", selectedItem.BUSINESS_DESCRIPTION)
                intent.putExtra("mail", selectedItem.BUSINESS_EMAIL)
                intent.putExtra("Website", selectedItem.BUSINESS_WEBSITE)
                intent.putExtra("address", selectedItem.BUSINESS_ADDRESS)

                intent.putStringArrayListExtra("imageUrls", imageUrls)

                // ADD CONTACT DETAILS

                startActivity(intent)
            }
        }

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this implementation
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed for this implementation
            }

            override fun afterTextChanged(s: Editable?) {
                // Filter activityDataList based on the search query
                val searchText = s.toString().toLowerCase()
                val filteredList = businessDataList.filter {
                    it.BUSINESS_NAME.toLowerCase().contains(searchText)
                }

                // Update the adapter with the filtered list
                businessAdapter.updateData(filteredList)
            }
        })
    }
    //............................................................................................//
}