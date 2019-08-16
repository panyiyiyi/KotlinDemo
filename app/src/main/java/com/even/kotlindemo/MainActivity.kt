package com.even.kotlindemo

import com.even.common.base.BaseLazyFragment
import com.even.kt_common.base.BaseKtActivity
import com.even.kt_common.router.ARouterManagerUtils
import com.even.kt_common.router.ARouterPath
import com.even.kt_common.router.provider.IArticleProvider
import com.even.kt_common.router.provider.IHomeProvider
import com.even.kt_common.router.provider.IProjectProvider

class MainActivity : BaseKtActivity() {
    var fragmentLists = mutableListOf<BaseLazyFragment>()

    private var homeProvider: IHomeProvider? = null
    private var projectProvider: IProjectProvider? = null
    private var articleProvider: IArticleProvider? = null

    override fun getContentView(): Int = R.layout.activity_main


    override fun getLogicClazz(): Class<*>? = null

    override fun initView() {
        initFragment()
    }

    private fun initFragment() {
        homeProvider = ARouterManagerUtils.getProvider(ARouterPath.HOME_SERVICE_PATH)
        projectProvider = ARouterManagerUtils.getProvider(ARouterPath.PROJECT_SERVICE_PATH)
        articleProvider = ARouterManagerUtils.getProvider(ARouterPath.ARTICLE_SERVICE_PATH)
        homeProvider?.let { fragmentLists.add(homeProvider!!.getHomeFragment()) }
        projectProvider?.let { fragmentLists.add(projectProvider!!.getProjectFragment()) }
        articleProvider?.let { fragmentLists.add(articleProvider!!.getArticleFragment()) }

    }
}
