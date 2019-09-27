package com.even.kt_wxarticle

import com.even.common.base.BaseFragment
import com.even.kt_wxarticle.beans.ArticleTabBean
import com.even.kt_wxarticle.ui.adapter.TabViewPagerAdapter
import com.even.kt_wxarticle.ui.fragment.ListFragment
import com.even.kt_wxarticle.ui.presenters.ArticleFragmentPresenter
import com.even.kt_wxarticle.ui.views.ArticleFragmentView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
class ArticleFragment : BaseFragment(), ArticleFragmentView {
    private var fgLists = mutableListOf<BaseFragment>()
    private var tabNameLists = mutableListOf<String>()

    override fun getContentView(): Int = R.layout.fragment_article

    override fun getLogicClazz(): Class<*> = ArticleFragmentView::class.java

    override fun initView() {
    }

    override fun initData() {
        (mPresenter as ArticleFragmentPresenter).getArticleTab()
    }

    override fun getTabSuccess(tabLists: List<ArticleTabBean>) {
        tabLists.forEach {
            fgLists.add(ListFragment(it.id.toString()))
            tabNameLists.add(it.name)
        }

        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        mViewPager.adapter = TabViewPagerAdapter(fragmentManager!!, fgLists, tabNameLists)
        tabLayout.setupWithViewPager(mViewPager)

//        viewPager.adapter = TabViewPagerAdapter(fragmentManager!!, fgLists, lifecycle)
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                tabLayout.setScrollPosition(position, 0f, true)
//            }
//        })
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = tabLists[position].name
//        }.attach()
    }
}