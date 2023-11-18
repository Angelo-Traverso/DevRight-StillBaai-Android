package com.example.devright_stillbaaitourism

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EventData
{
    var EVENT_ID: Int = 0
    var EVENT_NAME: String = ""
    var EVENT_NUM: String? = null
    var EVENT_EMAIL: String? = null
    var EVENT_WEBSITE: String? = null
    var EVENT_ADDRESS: String = ""
    var EVENT_PERSON: String? = null
    var EVENT_DATE: String? = null
    var EVENT_STARTTIME: String? = null
    var EVENT_DURATION: String? = null
    var EVENT_DESCRIPTION: String? = null
    var EVENT_IMAGE_URLS: MutableList<String> = mutableListOf()


    @RequiresApi(Build.VERSION_CODES.O)
    fun isEventToday(): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = LocalDate.now().format(formatter)
        return EVENT_DATE == currentDate
    }


}