package com.example.devright_stillbaaitourism

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class EventPagerAdapter(fm: FragmentManager, private val eventFragments: List<EventFragment>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return eventFragments[position]
    }

    override fun getCount(): Int {
        return eventFragments.size
    }
}