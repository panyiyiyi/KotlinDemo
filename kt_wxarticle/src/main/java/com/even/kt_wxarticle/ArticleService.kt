package com.even.kt_wxarticle

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.even.common.base.BaseLazyFragment
import com.even.kt_common.router.ARouterPath
import com.even.kt_common.router.provider.IArticleProvider

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
@Route(path = ARouterPath.ARTICLE_SERVICE_PATH, name = "公众号")
class ArticleService : IArticleProvider {
    override fun getArticleFragment(): BaseLazyFragment = ArticleFragment()

    override fun init(context: Context?) {
    }
}