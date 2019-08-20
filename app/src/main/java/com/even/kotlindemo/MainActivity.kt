package com.even.kotlindemo

import android.view.KeyEvent
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.even.common.base.BaseFragment
import com.even.common.utils.ActivityManagerUtils
import com.even.common.utils.ToastUtils
import com.even.common.utils.UiUtils
import com.even.kotlindemo.adapter.MainViewPagerAdapter
import com.even.kt_common.base.BaseKtActivity
import com.even.kt_common.router.ARouterManagerUtils
import com.even.kt_common.router.ARouterPath
import com.even.kt_common.router.provider.IArticleProvider
import com.even.kt_common.router.provider.IHomeProvider
import com.even.kt_common.router.provider.IProjectProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseKtActivity() {
    private var fragmentLists = mutableListOf<BaseFragment>()
    private var exitTime = 0L


    private var homeProvider: IHomeProvider? = null
    private var projectProvider: IProjectProvider? = null
    private var articleProvider: IArticleProvider? = null

    override fun getContentView(): Int = R.layout.activity_main

    override fun getLogicClazz(): Class<*>? = null

    override fun initView() {
        initFragment()

        bottomView.setOnNavigationItemSelectedListener { setBottomNavigationViewItemSelect(it) }
        viewPager.adapter = MainViewPagerAdapter(supportFragmentManager, fragmentLists, lifecycle)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomView.selectedItemId = bottomView.menu.getItem(position).itemId
            }
        })
        viewPager.isUserInputEnabled = false
    }

    private fun setBottomNavigationViewItemSelect(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.bottom_home -> {
                viewPager.currentItem = 0
                tvTitle.text = UiUtils.getString(R.string.home)
                true
            }
            R.id.bottom_article -> {
                viewPager.currentItem = 1
                tvTitle.text = UiUtils.getString(R.string.article)
                true
            }
            R.id.bottom_project -> {
                viewPager.currentItem = 2
                tvTitle.text = UiUtils.getString(R.string.project)
                true
            }
            else -> false
        }
    }


    private fun initFragment() {
        homeProvider = ARouterManagerUtils.getProvider(ARouterPath.HOME_SERVICE_PATH)
        articleProvider = ARouterManagerUtils.getProvider(ARouterPath.ARTICLE_SERVICE_PATH)
        projectProvider = ARouterManagerUtils.getProvider(ARouterPath.PROJECT_SERVICE_PATH)
        homeProvider?.let { fragmentLists.add(homeProvider!!.getHomeFragment()) }
        articleProvider?.let { fragmentLists.add(articleProvider!!.getArticleFragment()) }
        projectProvider?.let { fragmentLists.add(projectProvider!!.getProjectFragment()) }
    }

    override fun useDefaultTitleBar(): Boolean = false

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit()
            return false
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun exit() {
        if (System.currentTimeMillis().minus(exitTime) > EXIT_INTERVAL_TIME) {
            ToastUtils.showLong("再按一次退出程序")
            exitTime = System.currentTimeMillis()
        } else {
            ActivityManagerUtils.closeAllActivity()
        }
    }

    companion object {
        private const val EXIT_INTERVAL_TIME = 2000
    }
}
