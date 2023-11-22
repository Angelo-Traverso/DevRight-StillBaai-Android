package com.example.devright_stillbaaitourism

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
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

        /**
         * Hides the soft keyboard and clears focus from the specified EditText.
         *
         * @param context The context of the calling activity or fragment.
         * @param view The view that currently has focus.
         * @param etSearch The EditText for which the keyboard should be hidden.
         */
        fun hideUserKeyboard(context: Context, view: View, etSearch: EditText) {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            etSearch.clearFocus()
        }

        /**
         * Opens Google Maps with the specified address, if the Google Maps app is installed.
         * Otherwise, displays a toast indicating that Google Maps is not installed.
         *
         * @param context The context of the calling activity or fragment.
         * @param address The address to be displayed on Google Maps.
         */
        fun openGoogleMaps(context: Context, address: String) {
            // Create a Uri for the location (use the "q" parameter)
            val uri = Uri.parse("geo:0,0?q=$address")

            // Create an Intent to view the location on Google Maps
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)

            // Set the package to specify that you want to use Google Maps
            mapIntent.setPackage("com.google.android.apps.maps")

            // Check if there's an app available to handle the intent
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                // Start the intent to open Google Maps
                context.startActivity(mapIntent)
            } else {
                Toast.makeText(context, "Google Maps is not installed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //