package com.example.devright_stillbaaitourism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*

class Businesses : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var businessAdapter: BusinessAdapter
    private lateinit var burgerMenu: BurgerMenu

    // Store the filtered list at the class level
    private var filteredBusinessList: List<BusinessData> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_businesses)
        burgerMenu.setupDrawer()

        val businessDataList = GlobalClass.BusinessDataList
        val btnFilter = findViewById<ImageButton>(R.id.btnFilter)
        val edtSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<ImageButton>(R.id.btnSearch)

        listView = findViewById(R.id.businessListView)

        businessAdapter = BusinessAdapter(this, businessDataList)

        // Initially, use the unfiltered list
        filteredBusinessList = businessDataList
        listView.adapter = businessAdapter

        /*
     * List view on click listener
     * */
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < filteredBusinessList.size) {
                val selectedItem = filteredBusinessList[position]

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
                val contactNumber: String = if (!selectedItem.BUSINESS_MOBILE_NUM.isNullOrBlank()){

                    selectedItem.BUSINESS_MOBILE_NUM?:""
                }else
                {
                    selectedItem.BUSINESS_TELL_NUM?:""
                }

                intent.putExtra("ContactNumber", contactNumber)

                // ADD CONTACT DETAILS

                startActivity(intent)
            }
        }

        /*
        * Search click event
        */
        btnSearch.setOnClickListener {
            GlobalClass.hideUserKeyboard(this, edtSearch, edtSearch)
        }

        /*
        * Filter to let users filter by category
        * */
        btnFilter.setOnClickListener {
            val popupMenu = PopupMenu(this, btnFilter)

            // Get unique categories from your businessDataList
            val categories = businessDataList.map { it.BUSINESS_CATEGORY_TYPE }.distinct()
            popupMenu.menu.add("All")
            // Create menu items dynamically
            for (category in categories) {
                popupMenu.menu.add(category)
            }

            /*
            * popupMenu on item select listener, this will filter the list to match the user required category
            * */
            popupMenu.setOnMenuItemClickListener { item ->
                // Handle menu item click
                val selectedCategory = item.title.toString()
                filteredBusinessList = if (selectedCategory.equals("All", ignoreCase = true)) {
                    businessDataList
                } else {
                    businessDataList.filter {
                        it.BUSINESS_CATEGORY_TYPE.equals(selectedCategory, ignoreCase = true)
                    }
                }
                businessAdapter.updateData(filteredBusinessList)
                true
            }

            popupMenu.show()
        }

        /*
         * Filter to let users filter by category
         * */
        btnFilter.setOnClickListener {
            val popupMenu = PopupMenu(this, btnFilter)

            // Get unique categories from your businessDataList
            val categories = businessDataList.map { it.BUSINESS_CATEGORY_TYPE }.distinct()
            popupMenu.menu.add("All")
            // Create menu items dynamically
            for (category in categories) {
                popupMenu.menu.add(category)
            }

            /*
            * popupMenu on item select listener, this will filter the list to match the user required category
            * */
            popupMenu.setOnMenuItemClickListener { item ->
                // Handle menu item click
                val selectedCategory = item.title.toString()
                filteredBusinessList = if (selectedCategory.equals("All", ignoreCase = true)) {
                    businessDataList
                } else {
                    businessDataList.filter {
                        it.BUSINESS_CATEGORY_TYPE.equals(selectedCategory, ignoreCase = true)
                    }
                }
                businessAdapter.updateData(filteredBusinessList)
                true
            }

            popupMenu.show()
        }
    }
    //............................................................................................//
}