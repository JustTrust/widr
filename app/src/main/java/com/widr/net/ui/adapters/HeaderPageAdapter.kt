package com.widr.net.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.widr.net.ui.fragment.FeedFragment


/**
 * Created by a.belichenko on 13.04.2019.
 * mail: a.belichenko@gmail.com
 */
class HeaderPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return FeedFragment()
    }
}