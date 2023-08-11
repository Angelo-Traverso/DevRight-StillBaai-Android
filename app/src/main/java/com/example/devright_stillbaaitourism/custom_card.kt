package com.example.devright_stillbaaitourism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class custom_card @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,  var isTimerRunning: Boolean = false


) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.custom_card, this, true)
    }

    // Add code to dynamically change attributes on card

}