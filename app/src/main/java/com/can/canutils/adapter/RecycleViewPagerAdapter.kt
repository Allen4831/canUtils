package com.can.canutils.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.can.canutils.fragment.RecycleViewFragment

/**
 * Created by CAN on 2020/1/16.
 */
class RecycleViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return RecycleViewFragment()
    }

    override fun getCount(): Int {
        return 4
    }
}