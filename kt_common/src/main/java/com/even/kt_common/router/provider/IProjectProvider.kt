package com.even.kt_common.router.provider

import com.alibaba.android.arouter.facade.template.IProvider
import com.even.common.base.BaseFragment

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *  项目体系
 */
interface IProjectProvider : IProvider {
    /**
     * 获取Fragment
     */
    fun getProjectFragment(): BaseFragment

    /**
     * 打开菜单
     */
    fun openMenu()
}