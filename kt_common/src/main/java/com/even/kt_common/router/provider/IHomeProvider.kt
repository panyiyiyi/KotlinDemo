package com.even.kt_common.router.provider

import com.alibaba.android.arouter.facade.template.IProvider
import com.even.common.base.BaseLazyFragment

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *  首页
 */
interface IHomeProvider : IProvider {
    /**
     * 获取Fragment对象
     */
    fun getHomeFragment(): BaseLazyFragment
}