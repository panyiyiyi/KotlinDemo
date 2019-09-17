package com.even.kt_home.ui.api

import com.even.kt_common.base.PageBean
import com.even.kt_common.base.ResponseBean
import com.even.kt_home.ui.beans.BannerBean
import com.even.kt_home.ui.beans.HomeListBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author  Created by Even on 2019/9/17
 *  Email: emailtopan@163.com
 *
 */
interface HomeApiService {
    /**
     * 获取Banner图片
     */
    @GET("/banner/json")
    fun getBanner(): Observable<ResponseBean<MutableList<BannerBean>>>

    /**
     * 获取首页文章
     */
    @GET("/article/list/{pageNo}/json")
    fun getArticleList(@Path("pageNo") PageNo: Int): Observable<PageBean<HomeListBean>>
}