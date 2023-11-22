package com.example.devright_stillbaaitourism

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Adapter class for managing fragments in the ViewPager used for displaying events.
 *
 * @param fm The FragmentManager to interact with fragment transactions.
 * @param eventFragments List of AdvertFragments to be displayed in the ViewPager.
 */
class EventPagerAdapter(fm: FragmentManager, private val eventFragments: List<AdvertFragment>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    /**
     * Returns the fragment at the specified position in the ViewPager.
     *
     * @param position The position of the fragment to retrieve.
     * @return The fragment at the specified position.
     */
    override fun getItem(position: Int): Fragment {
        return eventFragments[position]
    }


    /**
     * Returns the total number of fragments in the ViewPager.
     *
     * @return The total number of fragments.
     */
    override fun getCount(): Int {
        return eventFragments.size
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //