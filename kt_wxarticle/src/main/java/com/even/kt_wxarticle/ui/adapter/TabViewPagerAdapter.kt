package com.even.kt_wxarticle.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author  Created by Even on 2019/9/27
 *  Email: emailtopan@163.com
 *
 */
class TabViewPagerAdapter<T : Fragment>(
    fm: FragmentManager,
    private val fgLists: List<T>,
    private val tabNameLists: List<String>
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment = fgLists[position]
    override fun getCount(): Int = fgLists.size
    override fun getPageTitle(position: Int): CharSequence {
        return tabNameLists[position]
    }
}