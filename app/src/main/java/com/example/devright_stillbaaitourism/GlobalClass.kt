package com.example.devright_stillbaaitourism

import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.internal.ViewUtils.hideKeyboard

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

        fun hideUserKeyboard(context: Context, view: View, etSearch: EditText) {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            etSearch.clearFocus()
        }

    }




}