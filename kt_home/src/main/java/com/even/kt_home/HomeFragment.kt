package com.even.kt_home

import com.even.common.base.BaseLazyFragment

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *  主页fragment
 */
class HomeFragment : BaseLazyFragment() {
    override fun getContentView(): Int = R.layout.fragment_home

    override fun getLogicClazz(): Class<*>? = null

    override fun initView() {
    }
}