package com.example.devright_stillbaaitourism

/**
 * Data class representing an activity.
 */
class ActivityData
{
    var ACTIVITY_ID: Int = 0
    var ACTIVITY_NAME: String = ""
    var ACTIVITY_TEL_NUM: String? = null
    var ACTIVITY_MOBILE_NUM: String? = null
    var ACTIVITY_EMAIL: String? = null
    var ACTIVITY_WEBSITE: String? = null
    var ACTIVITY_ADDRESS: String? = null
    var ACTIVITY_CONTACT_PERSON: String? = null
    var ACTIVITY_DESCRIPTION: String? = null
    var ACTIVITY_CATEGORY_ID: Int = 0
    var ACTIVITY_IMAGE_URLS: MutableList<String> = mutableListOf()
    var ACTIVITY_CATEGORY_TYPE: String?=null

}