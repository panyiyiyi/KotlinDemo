package com.even.kt_home.ui.presenters

import com.even.common.base.BasePresenter
import com.even.common.request.observer.Transformer
import com.even.common.request.utils.RxHttpUtils
import com.even.kt_common.base.PageObserver
import com.even.kt_common.base.ResponseObserver
import com.even.kt_home.ui.api.HomeApiService
import com.even.kt_home.ui.beans.BannerBean
import com.even.kt_home.ui.beans.HomeListBean
import com.even.kt_home.ui.views.HomeView

/**
 * @author  Created by Even on 2019/9/17
 *  Email: emailtopan@163.com
 *
 */
class HomePresenter : BasePresenter<HomeView>() {
    /**
     * 获取banner图片
     */
    fun getBanner() {
        RxHttpUtils.createApi(HomeApiService::class.java)
            .getBanner()
            .compose(Transformer.switchSchedulers())
            .subscribe(object : ResponseObserver<MutableList<BannerBean>>(RxTag) {
                override fun doSuccess(t: MutableList<BannerBean>) {
                    getView()?.getBannerSuccess(t)
                }
            })
    }

    /**
     * 获取推荐文章列表
     */
    fun getHomeArticle(pageNo: Int) {
        RxHttpUtils.createApi(HomeApiService::class.java)
            .getArticleList(pageNo)
            .compose(Transformer.switchSchedulers())
            .subscribe(object : PageObserver<HomeListBean>(RxTag) {
                override fun doSuccess(pageTotal: Int, dataLists: List<HomeListBean>) {
                    getView()?.getHomeArticle(dataLists, pageNo, pageTotal)
                }

                override fun doCompleted() {
                    super.doCompleted()
                    getView()?.loadComplete()
                }
            })
    }
}