package com.even.kt_wxarticle.api

import com.even.kt_common.base.PageBean
import com.even.kt_common.base.ResponseBean
import com.even.kt_wxarticle.beans.ArticleListBean
import com.even.kt_wxarticle.beans.ArticleTabBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

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
    fun getArticles(): Observable<ResponseBean<List<ArticleTabBean>>>

    /**
     * 获取指定公众号的历史
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    fun getArticleLists(@Path("id") id: String, @Path("page") page: Int): Observable<PageBean<ArticleListBean>>
}