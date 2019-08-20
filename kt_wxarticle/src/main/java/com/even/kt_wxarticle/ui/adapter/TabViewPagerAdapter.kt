package com.even.kt_wxarticle.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.even.common.base.BaseFragment


/**
 * @author  Created by Even on 2019/8/20
 *  Email: emailtopan@163.com
 *
 */
class TabViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fgLists: List<BaseFragment>,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = fgLists.size
    override fun createFragment(position: Int): Fragment = fgLists[position]


}