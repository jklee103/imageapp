package com.example.jkapplication.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.jkapplication.view.fragments.*

class PagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    var fm = fm
    var behavior=behavior
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return GlideFragment()
            1 -> return PicassoFragment()
            2 -> return FrescoFragment()
            3 -> return ButtonFragment()
            4 -> return MoreFragment()
            5 -> return ScrollFragment()
            else -> return GlideFragment()

        }
    }

    override fun getCount(): Int {
        return behavior
    }
}