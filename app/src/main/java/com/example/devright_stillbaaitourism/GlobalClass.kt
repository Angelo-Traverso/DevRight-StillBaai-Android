package com.example.devright_stillbaaitourism

import android.app.Application

class GlobalClass: Application()
{
    companion object
    {
        var ActivityDataList = arrayListOf<ActivityData>()
        var BusinessDataList = arrayListOf<BusinessData>()
        var ContactDataList = arrayListOf<ContactData>()
        var EatDataList = arrayListOf<EatData>()
        var EelDataList = arrayListOf<EelData>()
        var EventDataList = arrayListOf<EventData>()
        var StayDataList = arrayListOf<StayData>()
        var ListingDataList = arrayListOf<ListingImagesData>()
    }
}