package com.example.devright_stillbaaitourism

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
class Stay : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var stayAdapter: StayAdapter
    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stay)
        burgerMenu = BurgerMenu(this, R.layout.activity_stay)
        burgerMenu.setupDrawer()

        val edtSearch = findViewById<EditText>(R.id.etSearch)

        listView = findViewById(R.id.stayListView)


        val btnSearch = findViewById<ImageButton>(R.id.btnSearch)
        btnSearch.setOnClickListener{
            hideKeyboard()
            edtSearch.clearFocus()
        }

        val stayDataList = GlobalClass.StayDataList




        stayAdapter = StayAdapter(this, stayDataList)

        listView.adapter = stayAdapter


        // Item click listener for the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < stayDataList.size) {
                val selectedItem = stayDataList[position]

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
                val contactNumber: String = if (!selectedItem.STAY_MOBILE_NUM.isNullOrBlank()){

                    selectedItem.STAY_MOBILE_NUM?:""
                }else
                {
                    selectedItem.STAY_TEL_NUM?:""
                }

                intent.putExtra("ContactNumber", contactNumber)
                startActivity(intent)
            }
        }

    }
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
    //............................................................................................//

}