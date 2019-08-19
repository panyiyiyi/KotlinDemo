package com.even.kt_common.router.provider

import com.alibaba.android.arouter.facade.template.IProvider
import com.even.common.base.BaseFragment

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
interface IArticleProvider : IProvider {
    /**
     * 获取公众号fragment
     */
    fun getArticleFragment(): BaseFragment
}