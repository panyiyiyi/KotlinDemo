package com.even.kt_home

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.even.common.base.BaseFragment
import com.even.kt_common.router.ARouterPath
import com.even.kt_common.router.provider.IHomeProvider

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *  home路由Provider
 */
@Route(path = ARouterPath.HOME_SERVICE_PATH, name = "首页")
class HomeService : IHomeProvider {
    override fun getHomeFragment(): BaseFragment = HomeFragment()

    override fun init(context: Context?) {
    }
}