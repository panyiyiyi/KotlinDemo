package com.even.kt_wxarticle.ui.presenters

import com.even.common.base.BasePresenter
import com.even.common.request.observer.Transformer
import com.even.common.request.utils.RxHttpUtils
import com.even.kt_common.base.ResponseObserver
import com.even.kt_wxarticle.api.ApiService
import com.even.kt_wxarticle.beans.ArticleTabBean
import com.even.kt_wxarticle.ui.views.ArticleFragmentView

/**
 * @author  Created by Even on 2019/8/19
 *  Email: emailtopan@163.com
 *
 */
class ArticleFragmentPresenter : BasePresenter<ArticleFragmentView>() {
    /**
     * 获取公众号列表
     */
    fun getArticleTab() {
        RxHttpUtils.createApi(ApiService::class.java)
            .getArticles()
            .compose(Transformer.switchSchedulers())
            .subscribe(object : ResponseObserver<List<ArticleTabBean>>(RxTag) {
                override fun doSuccess(t: List<ArticleTabBean>) {
                    getView()?.getTabSuccess(t)
                }
            })

    }
}