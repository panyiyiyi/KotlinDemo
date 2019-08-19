package com.even.kt_wxarticle.ui.views

import com.even.common.base.BaseView
import com.even.common.base.model.Implement
import com.even.kt_wxarticle.beans.ArticleTabBean
import com.even.kt_wxarticle.ui.presenters.ArticleFragmentPresenter

/**
 * @author  Created by Even on 2019/8/19
 *  Email: emailtopan@163.com
 *
 */
@Implement(ArticleFragmentPresenter::class)
open interface ArticleFragmentView : BaseView {
    /**
     * 获取公众号Tab成功
     */
    open fun getTabSuccess(tabLists: List<ArticleTabBean>)
}