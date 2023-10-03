package com.example.devright_stillbaaitourism

import CustomAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

class Eat : AppCompatActivity(){

    private lateinit var apiService: ApiService

    private lateinit var binding: ActivityEatBinding

    private lateinit var burgerMenu: BurgerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_eat)
        burgerMenu.setupDrawer()


        CoroutineScope(Dispatchers.IO).launch {
            val dbHandler = DBHandler()
            val conn = dbHandler.getConnection()
            val temp = conn?.let { dbHandler.fetchEatData() }
            // Do something with 'temp'
        }
        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://stilbaaitourism.co.za/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of ApiService
        apiService = retrofit.create(ApiService::class.java)

        // Make the API request to fetch media items
        fetchMediaItems()

        // ----------------------- List View ----------------------- //
        val listView = findViewById<ListView>(R.id.listView)

        // List items
        val items = listOf(
            ListItem(R.drawable.thumbail, "Pizza World", "Fun in the sun with Pizza"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
        )


        /* val adapter = CustomAdapter(this, items)
         listView.adapter = adapter*/

        // Set item click listener for the ListView
        // Set item click listener for the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position >= 0 && position < items.size) {
                val selectedItem = items[position]

                // Create an Intent to open the DetailActivity
                val intent = Intent(this@Eat, DetailActivity::class.java)

                // Pass data to the DetailActivity using Intent extras
                intent.putExtra("title", selectedItem.title)
                intent.putExtra("description", selectedItem.description)
                intent.putExtra("thumbnailResId", selectedItem.thumbnailResId)

                startActivity(intent)
            }
        }
        // ----------------------- END List View ----------------------- //


    }

    private fun fetchMediaItems() {
        // Make the API request to fetch media items
        val call = apiService.getMediaItems()
        // enqueue() is used to asynchronously make the API request and handle responses
        call.enqueue(object : Callback<List<MediaItem>> {
            override fun onResponse(
                call: Call<List<MediaItem>>,
                response: Response<List<MediaItem>>
            ) {
                if (response.isSuccessful) {
                    // If the API request is successful (HTTP status code 200)
                    val mediaItems = response.body()
                    if (mediaItems != null) {
                        // Extract "thumbnail" URLs from mediaItems
                        val thumbnailUrls =
                            mediaItems.mapNotNull { it.media_details.sizes["thumbnail"]?.source_url }

                        // Initialize the adapter with the current context and "thumbnail" URLs
                        val adapter = CustomAdapter(this@Eat, thumbnailUrls)

                        // Find the ListView by its ID
                        val listView = findViewById<ListView>(R.id.listView)

                        // Set the adapter for the ListView
                        listView.adapter = adapter

                        // Notify the adapter that the data has changed
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<List<MediaItem>>, t: Throwable) {
                // If there is a network or other failure in making the API request
                // Handle network error
                // Can add network error handling here, such as displaying a network error message
            }
        })
    }
}
