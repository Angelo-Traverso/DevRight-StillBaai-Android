package com.example.devright_stillbaaitourism

/**
 * Data class representing a Business.
 */
class BusinessData
{
    var BUSINESS_ID: Int = 0
    var BUSINESS_NAME: String = ""
    var BUSINESS_TELL_NUM: String? = null
    var BUSINESS_MOBILE_NUM: String? = null
    var BUSINESS_EMAIL: String? = null
    var BUSINESS_WEBSITE: String? = null
    var BUSINESS_ADDRESS: String = ""
    var BUSINESS_CONTACT_PERSON: String? = null
    var BUSINESS_DESCRIPTION: String? = null
    var BUSINESS_CATEGORY_ID: Int = 0
    var BUSINESS_CATEGORY_TYPE: String? = null
    var BUSINESS_IMAGE_URLS: MutableList<String> = mutableListOf()
}

