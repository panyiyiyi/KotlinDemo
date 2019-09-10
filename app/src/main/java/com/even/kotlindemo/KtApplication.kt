package com.even.kotlindemo

import android.content.Context
import com.even.common.base.BaseApplication
import com.even.common.utils.UiUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
class KtApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        initLog(BuildConfig.DEBUG)
        initRouter(BuildConfig.DEBUG)
        initHttpUtils("https://www.wanandroid.com", BuildConfig.DEBUG)


        initSmartRefresh()
    }

    /**
     * 初始化加载控件
     */
    private fun initSmartRefresh() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            ClassicsHeader(context).setAccentColor(UiUtils.getColor(R.color.color26B7BC))
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(context).setAccentColor(
                UiUtils.getColor(R.color.color26B7BC)
            )
        }
    }
}