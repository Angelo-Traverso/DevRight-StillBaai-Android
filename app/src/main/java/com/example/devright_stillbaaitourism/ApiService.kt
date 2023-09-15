package com.example.devright_stillbaaitourism

import retrofit2.Call
import retrofit2.http.GET

// API Service
interface ApiService {
    // Define a GET request to fetch media items
    @GET("wp-json/wp/v2/media?per_page=100&page=1")
    fun getMediaItems(): Call<List<MediaItem>>
}