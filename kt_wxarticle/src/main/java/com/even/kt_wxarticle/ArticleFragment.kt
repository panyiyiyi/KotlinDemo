package com.even.kt_wxarticle

import com.even.common.base.BaseLazyFragment

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
class ArticleFragment : BaseLazyFragment() {
    override fun getContentView(): Int = R.layout.fragment_article

    override fun getLogicClazz(): Class<*>? = null

    override fun initView() {
    }
}