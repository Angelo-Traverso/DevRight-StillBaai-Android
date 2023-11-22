package com.example.devright_stillbaaitourism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView

class Eat : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu
    private lateinit var listview: ListView
    private lateinit var customAdapter: CustomAdapter
    private var filteredEatList: List<EatData> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eat)
        burgerMenu = BurgerMenu(this, R.layout.activity_eat)
        burgerMenu.setupDrawer()

        val edtSearch = findViewById<EditText>(R.id.etSearchEat)
        val btnSearch = findViewById<ImageButton>(R.id.btnSearch)
        val eatDataList = GlobalClass.EatDataList

        listview = findViewById(R.id.listView)
        customAdapter = CustomAdapter(this, eatDataList)
        filteredEatList = GlobalClass.EatDataList
        listview.adapter = customAdapter

        /**
         * On Click for listView items. Intents user to detail view, passing all data needed
         */
        listview.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < filteredEatList.size) {

                val selectedItem = filteredEatList[position]
                val intent = Intent(this@Eat, DetailActivity::class.java)
                val imageUrls = ArrayList(selectedItem.EAT_IMAGE_URLS)

                // Passing data to the DetailActivity using Intent extras
                intent.putExtra("title", selectedItem.EAT_NAME)
                intent.putExtra("description", selectedItem.EAT_DESCRIPTION)
                intent.putExtra("imageUrl", selectedItem.EAT_IMAGE_URLS[0])
                intent.putExtra("WebsiteURL", selectedItem.EAT_WEBSITE)
                intent.putExtra("address", selectedItem.EAT_ADDRESS)

                // Just added
                intent.putExtra("email", selectedItem.EAT_EMAIL)
                intent.putStringArrayListExtra("imageUrls", imageUrls)

                // Use either mobile number or tell number, whichever is available
                val contactNumber: String = if (!selectedItem.EAT_MOBILE_NUM.isNullOrBlank()) {

                    selectedItem.EAT_MOBILE_NUM ?: ""
                } else {
                    selectedItem.EAT_TEL_NUM ?: ""
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
                filteredEatList = eatDataList.filter {
                    it.EAT_NAME.toLowerCase().contains(searchText)
                }

                // Update the adapter with the filtered list
                customAdapter.updateData(filteredEatList)

            }
        })
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //
