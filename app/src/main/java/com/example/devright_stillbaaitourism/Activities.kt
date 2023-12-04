package com.example.devright_stillbaaitourism

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import kotlin.collections.ArrayList

class Activities : AppCompatActivity() {

    private lateinit var activitiesAdapter: ActivityAdapter
    private lateinit var listView: ListView
    private lateinit var burgerMenu: BurgerMenu
    private var filteredActivityList: List<ActivityData> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_activities)
        burgerMenu.setupDrawer()

        // ------------------ Bindings ------------------ //
        val etSearch = findViewById<EditText>(R.id.etSearch)
        val activityDataList = GlobalClass.ActivityDataList
        val btnSearch = findViewById<ImageButton>(R.id.btnSearch)
        val btnFilter = findViewById<ImageButton>(R.id.btnFilter)

        listView = findViewById(R.id.activityListView)
        activitiesAdapter = ActivityAdapter(this, activityDataList)
        filteredActivityList = activityDataList
        listView.adapter = activitiesAdapter

        /**
         * On Click for listView items. Intents user to detail view, passing all data needed
         */
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < filteredActivityList.size) {
                val selectedItem = filteredActivityList[position]

                // Creating an Intent to open the DetailActivity
                val intent = Intent(this@Activities, ActivitiesDetailActivity::class.java)
                val imageUrls = ArrayList(selectedItem.ACTIVITY_IMAGE_URLS)

                // Passing data to the DetailActivity using Intent extras
                intent.putExtra("ActivityName", selectedItem.ACTIVITY_NAME)
                intent.putExtra("Description", selectedItem.ACTIVITY_DESCRIPTION)
                intent.putExtra("WebsiteLink", selectedItem.ACTIVITY_WEBSITE ?: "")
                intent.putExtra("email", selectedItem.ACTIVITY_EMAIL)
                intent.putExtra("address", selectedItem.ACTIVITY_ADDRESS)
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

        /*
        * Search click event
        */
        btnSearch.setOnClickListener{
            GlobalClass.hideUserKeyboard(this, etSearch , etSearch)
        }

        /*
        * Filter to let users filter by category
        * */
        btnFilter.setOnClickListener {
            val popupMenu = PopupMenu(this, btnFilter)

            // Get unique categories from your activityDataList
            val categories = activityDataList.map { it.ACTIVITY_CATEGORY_TYPE }.distinct()
            popupMenu.menu.add("All")

            // Create menu items dynamically
            for (category in categories) {
                popupMenu.menu.add(category)
            }

            // Handles the menu click event. List will filter according to user selection
            popupMenu.setOnMenuItemClickListener { item ->
                // Handle menu item click
                val selectedCategory = item.title.toString()
                filteredActivityList = if (selectedCategory.equals("All", ignoreCase = true)) {
                    activityDataList
                } else {
                    activityDataList.filter {
                        it.ACTIVITY_CATEGORY_TYPE.equals(selectedCategory, ignoreCase = true)
                    }
                }
                activitiesAdapter.updateData(filteredActivityList)
                true
            }
            popupMenu.show()
        }




        /**
         * Updates the displayed list to the user, according to their search key characters
         */
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this implementation
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed for this implementation
            }

            override fun afterTextChanged(s: Editable?) {
                // Filter activityDataList based on the search query
                val searchText = s.toString().toLowerCase()
                 filteredActivityList = activityDataList.filter {
                    it.ACTIVITY_NAME.toLowerCase().contains(searchText)
                }

                // Update the adapter with the filtered list
                activitiesAdapter.updateData(filteredActivityList)
            }
        })
    }


    /**
     * Updates the displayed list to the user, according to their search key characters
     */
    override fun attachBaseContext(newBase: Context) {
        val configuration = Configuration(newBase.resources.configuration)
        configuration.fontScale = 1f
        val context: Context = newBase.createConfigurationContext(configuration)
        super.attachBaseContext(context)
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //