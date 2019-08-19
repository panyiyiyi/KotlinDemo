package com.even.kt_wxarticle.api

import com.even.kt_common.base.BaseResponseBean
import com.even.kt_wxarticle.beans.ArticleTabBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author  Created by Even on 2019/8/19
 *  Email: emailtopan@163.com
 *
 */
interface ApiService {
    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/chapters/json")
    fun getArticles(): Observable<BaseResponseBean<List<ArticleTabBean>>>
}