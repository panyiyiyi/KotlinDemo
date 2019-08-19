package com.even.kt_wxarticle

import com.even.common.base.BaseFragment
import com.even.common.utils.LogUtils
import com.even.kt_wxarticle.beans.ArticleTabBean
import com.even.kt_wxarticle.ui.presenters.ArticleFragmentPresenter
import com.even.kt_wxarticle.ui.views.ArticleFragmentView

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
class ArticleFragment : BaseFragment(), ArticleFragmentView {


    override fun getContentView(): Int = R.layout.fragment_article

    override fun getLogicClazz(): Class<*> = ArticleFragmentView::class.java

    override fun initView() {
    }

    override fun initData() {
        (mPresenter as ArticleFragmentPresenter).getArticleTab()
    }

    override fun getTabSuccess(tabLists: List<ArticleTabBean>) {
        LogUtils.e(tabLists.toString())
    }
}