package com.example.devright_stillbaaitourism

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.*

class Businesses : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var businessAdapter: BusinessAdapter

    private lateinit var burgerMenu: BurgerMenu

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

        listView.adapter = businessAdapter


        /*
       * List view on click listener
       * */
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

        /*
        * Search click event
        */
        btnSearch.setOnClickListener{
            hideKeyboard()
            edtSearch.clearFocus()
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

            popupMenu.setOnMenuItemClickListener { item ->
                // Handle menu item click
                val selectedCategory = item.title.toString()
                val filteredList = if (selectedCategory.equals("All", ignoreCase = true)) {
                    businessDataList
                } else {
                    businessDataList.filter {
                        it.BUSINESS_CATEGORY_TYPE.equals(selectedCategory, ignoreCase = true)
                    }
                }
                businessAdapter.updateData(filteredList)
                true
            }

            popupMenu.show()
        }

        /*
       * Search filtering
       * */
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
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
    //............................................................................................//
}