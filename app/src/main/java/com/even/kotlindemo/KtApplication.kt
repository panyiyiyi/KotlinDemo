package com.even.kotlindemo

import com.even.common.base.BaseApplication

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
    }
}