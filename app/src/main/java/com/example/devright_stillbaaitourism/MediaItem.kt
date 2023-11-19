package com.example.devright_stillbaaitourism

// Represents a media item from the JSON response
data class MediaItem(
    val id: Int,            // Unique ID of the media item
    val guid: Guid,         // Contains the URL information
    val media_details: MediaDetails // Contains details about the media
)

// Represents the "guid" field in the media item
data class Guid(
    // The URL of the media item
    val rendered: String
)

// Represents the "media_details" field in the media item
data class MediaDetails(
    // A map of different image sizes
    val sizes: Map<String, ImageSize>
)

// Represents an individual image size
data class ImageSize(
    // The URL of the image in this size
    val source_url: String
)

