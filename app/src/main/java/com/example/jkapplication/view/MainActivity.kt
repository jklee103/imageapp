package com.example.jkapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.jkapplication.R
import com.example.jkapplication.view.fragments.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var viewPager:ViewPager2
    lateinit var pagerAdapter: PagerAdapter
    lateinit var context: Context
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var selectFragment:Fragment =
            GlideFragment()

        supportFragmentManager.beginTransaction().add(R.id.a_main_fl_frame,selectFragment).commit()
        tabLayout = findViewById(R.id.a_main_tl_tab)

        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                var position = tab?.position

                when(position){
                    0 -> selectFragment=
                        GlideFragment()
                    1 -> selectFragment=
                        PicassoFragment()
                    2 -> selectFragment=
                        FrescoFragment()
                    3 -> selectFragment=
                        ButtonFragment()
                    4 -> selectFragment=
                        MoreFragment()
                    5 -> selectFragment=
                        ScrollFragment()
                    else -> selectFragment=
                        GlideFragment()

                }

                supportFragmentManager.beginTransaction().replace(R.id.a_main_fl_frame,selectFragment).commit()
            }

        })
    }
}

