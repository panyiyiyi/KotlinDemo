package com.even.kotlindemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.even.common.base.BaseLazyFragment

/**
 * @author  Created by Even on 2019/8/19
 *  Email: emailtopan@163.com
 *  viewPager2适配器
 */
class MainViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fgLists: List<BaseLazyFragment>,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = fgLists.size
    override fun createFragment(position: Int): Fragment = fgLists[position]
}