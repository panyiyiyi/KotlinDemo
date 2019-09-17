package com.even.kt_home.ui.views

import com.even.common.base.BaseView
import com.even.common.base.model.Implement
import com.even.kt_home.ui.beans.BannerBean
import com.even.kt_home.ui.beans.HomeListBean
import com.even.kt_home.ui.presenters.HomePresenter

/**
 * @author  Created by Even on 2019/9/17
 *  Email: emailtopan@163.com
 *
 */
@Implement(HomePresenter::class)
interface HomeView : BaseView {
    /**
     * 获取banner图片成功
     */
    fun getBannerSuccess(bannerLists: MutableList<BannerBean>)

    /**
     * 获取首页推荐文章成功
     */
    fun getHomeArticle(articleLists: List<HomeListBean>, pageNo: Int, totalPages: Int)

    /**
     * 完成加载
     */
    fun loadComplete()
}