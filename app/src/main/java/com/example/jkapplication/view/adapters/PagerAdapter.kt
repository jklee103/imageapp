package com.example.jkapplication.view.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jkapplication.view.fragments.*

class PagerAdapter : FragmentStateAdapter{

    var mCount = 0

    constructor(fa: FragmentActivity?, count: Int) : super(fa!!) {
        mCount = count
    }

    constructor(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) : super(fragmentManager, lifecycle) {
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return GlideFragment.getInstance()
            1 -> return PicassoFragment.getInstance()
            2 -> return FrescoFragment.getInstance()
            3 -> return ButtonFragment.getInstance()
            4 -> return MoreFragment.getInstance()
            5 -> return ScrollFragment.getInstance()
            else -> return GlideFragment.getInstance()

        }
    }

}