package com.example.devright_stillbaaitourism

import CustomAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devright_stillbaaitourism.databinding.ActivityEatBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class Eat : AppCompatActivity(){

    private lateinit var burgerMenu: BurgerMenu

    private lateinit var listview: ListView

    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_eat)
        burgerMenu.setupDrawer()

        listview = findViewById(R.id.listView)
        val edtSearch = findViewById<EditText>(R.id.etSearch)


        val btnSearch = findViewById<ImageButton>(R.id.btnSearch)
        btnSearch.setOnClickListener{
            hideKeyboard()
            edtSearch.clearFocus()
        }

        // Instance of EatDataList
        val eatDataList = GlobalClass.EatDataList


        // Create a custom adapter with the EatData list
        customAdapter = CustomAdapter(this, eatDataList)


        // Set the adapter for the ListView
        listview.adapter = customAdapter

        // Item click listener for the ListView
        listview.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < eatDataList.size) {
                val selectedItem = eatDataList[position]

                // Creating an Intent to open the DetailActivity
                val intent = Intent(this@Eat, DetailActivity::class.java)
                val imageUrls = ArrayList(selectedItem.EAT_IMAGE_URLS)
                // Passing data to the DetailActivity using Intent extras
                intent.putExtra("title", selectedItem.EAT_NAME)
                intent.putExtra("description", selectedItem.EAT_DESCRIPTION)
                intent.putExtra("imageUrl", selectedItem.EAT_IMAGE_URLS[0])
                intent.putExtra("WebsiteURL", selectedItem.EAT_WEBSITE)
                intent.putExtra("address", selectedItem.EAT_ADDRESS)
                //Just added
                intent.putExtra("email", selectedItem.EAT_EMAIL)
                intent.putStringArrayListExtra("imageUrls", imageUrls)

                // Use either mobile number or tell number, whichever is available
                val contactNumber: String = if (!selectedItem.EAT_MOBILE_NUM.isNullOrBlank()){

                    selectedItem.EAT_MOBILE_NUM?:""
                }else
                {
                    selectedItem.EAT_TEL_NUM?:""
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
}
