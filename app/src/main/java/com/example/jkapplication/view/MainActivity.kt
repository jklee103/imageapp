package com.example.jkapplication.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.jkapplication.R
import com.example.jkapplication.view.adapters.PagerAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager2
    lateinit var pagerAdapter: PagerAdapter
    lateinit var context: Context
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

//        var selectFragment: Fragment =
//            GlideFragment()

//        supportFragmentManager.beginTransaction().add(R.id.a_main_fl_frame, selectFragment).commit()
        pagerAdapter= PagerAdapter(this,6)
        tabLayout = findViewById(R.id.a_main_tl_tab)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter= pagerAdapter
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = "#${(position + 1)}"
        }.attach()

        viewPager.reduceDragSensitivity()


//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                var position = tab?.position
//
//                when (position) {
//                    0 -> selectFragment =
//                        GlideFragment.getInstance()
//                    1 -> selectFragment =
//                        PicassoFragment.getInstance()
//                    2 -> selectFragment =
//                        FrescoFragment.getInstance()
//                    3 -> selectFragment =
//                        ButtonFragment.getInstance()
//                    4 -> selectFragment =
//                        MoreFragment.getInstance()
//                    5 -> selectFragment =
//                        ScrollFragment.getInstance()
//                    else -> selectFragment =
//                        GlideFragment()
//
//                }
//
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.a_main_fl_frame, selectFragment).commit()
//            }
//
//        })
    }

}

