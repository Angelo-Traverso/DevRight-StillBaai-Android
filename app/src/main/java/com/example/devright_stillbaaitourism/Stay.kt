package com.example.devright_stillbaaitourism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*

class Stay : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var stayAdapter: StayAdapter
    private lateinit var burgerMenu: BurgerMenu

    // Store the filtered list at the class level
    private var filteredStayList: List<StayData> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stay)
        burgerMenu = BurgerMenu(this, R.layout.activity_stay)
        burgerMenu.setupDrawer()

        val edtSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<ImageButton>(R.id.btnSearch)
        val btnFilter = findViewById<ImageButton>(R.id.btnFilter)
        val stayDataList = GlobalClass.StayDataList

        listView = findViewById(R.id.stayListView)

        stayAdapter = StayAdapter(this, stayDataList)

        // Initially, use the unfiltered list
        filteredStayList = stayDataList
        listView.adapter = stayAdapter

        // Item click listener for the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < filteredStayList.size) {
                val selectedItem = filteredStayList[position]

                // Creating an Intent to open the DetailActivity
                val intent = Intent(this@Stay, StayDetail::class.java)
                val imageUrls = ArrayList(selectedItem.STAY_IMAGE_URLS)

                // Passing data to the DetailActivity using Intent extras
                intent.putExtra("StayName", selectedItem.STAY_NAME)
                intent.putExtra("Description", selectedItem.STAY_DESCRIPTION)
                intent.putExtra("WebsiteLink", selectedItem.STAY_WEBSITE ?: "")
                intent.putStringArrayListExtra("imageUrls", imageUrls)
                intent.putExtra("email", selectedItem.STAY_EMAIL)
                intent.putExtra("address", selectedItem.STAY_ADDRESS)

                val contactNumber: String = if (!selectedItem.STAY_MOBILE_NUM.isNullOrBlank()) {
                    selectedItem.STAY_MOBILE_NUM ?: ""
                } else {
                    selectedItem.STAY_TEL_NUM ?: ""
                }

                intent.putExtra("ContactNumber", contactNumber)
                startActivity(intent)
            }
        }

        /*
        * Search click event
        */
        btnSearch.setOnClickListener{
            GlobalClass.hideUserKeyboard(this, edtSearch , edtSearch)
        }


        /*
       * Filter to let users filter by category
       */
        btnFilter.setOnClickListener {
            val popupMenu = PopupMenu(this, btnFilter)

            // Get unique categories from your businessDataList
            val categories = stayDataList.map { it.STAY_CATEGORY_TYPE }.distinct()
            popupMenu.menu.add("All")
            // Create menu items dynamically
            for (category in categories) {
                popupMenu.menu.add(category)
            }

            popupMenu.setOnMenuItemClickListener { item ->
                // Handle menu item click
                val selectedCategory = item.title.toString()
                filteredStayList = if (selectedCategory.equals("All", ignoreCase = true)) {
                    stayDataList
                } else {
                    stayDataList.filter {
                        it.STAY_CATEGORY_TYPE.equals(selectedCategory, ignoreCase = true)
                    }
                }
                stayAdapter.updateData(filteredStayList)
                true
            }

            popupMenu.show()
        }

        /*
        * Search filtering
        */
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
                val filteredList = stayDataList.filter {
                    it.STAY_NAME.toLowerCase().contains(searchText)
                }

                // Update the adapter with the filtered list
                stayAdapter.updateData(filteredList)
            }
        })
    }
}