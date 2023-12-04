package com.example.devright_stillbaaitourism

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*

class Businesses : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var businessAdapter: BusinessAdapter
    private lateinit var burgerMenu: BurgerMenu
    private var filteredBusinessList: List<BusinessData> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_businesses)
        burgerMenu.setupDrawer()

        // ------------------ Bindings ------------------ //
        val businessDataList = GlobalClass.BusinessDataList
        val btnFilter = findViewById<ImageButton>(R.id.btnFilter)
        val edtSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<ImageButton>(R.id.btnSearch)

        listView = findViewById(R.id.businessListView)
        businessAdapter = BusinessAdapter(this, businessDataList)
        filteredBusinessList = businessDataList
        listView.adapter = businessAdapter

        /**
         * On Click for listView items. Intents user to detail view, passing all data needed
         */
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < filteredBusinessList.size) {
                val selectedItem = filteredBusinessList[position]

                // Creating an Intent to open the DetailActivity
                val intent = Intent(this@Businesses, BusinessDetail::class.java)
                val imageUrls = ArrayList(selectedItem.BUSINESS_IMAGE_URLS)

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

                startActivity(intent)
            }
        }

        /**
         * Updates the displayed list to the user, according to their search key characters
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
                val searchText = s.toString().lowercase()
                filteredBusinessList = businessDataList.filter {
                    it.BUSINESS_NAME.lowercase().contains(searchText)
                }

                // Update the adapter with the filtered list
                businessAdapter.updateData(filteredBusinessList)
            }
        })

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
    }

    override fun attachBaseContext(newBase: Context) {
        val configuration = Configuration(newBase.resources.configuration)
        configuration.fontScale = 1f
        val context: Context = newBase.createConfigurationContext(configuration)
        super.attachBaseContext(context)
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //