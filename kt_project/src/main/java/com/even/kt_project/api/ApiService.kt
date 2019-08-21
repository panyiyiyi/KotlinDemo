package com.even.kt_project.api

import com.even.kt_common.base.PageBean
import com.even.kt_common.base.ResponseBean
import com.even.kt_project.beans.ProjectBean
import com.even.kt_project.beans.ProjectListBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author  Created by Even on 2019/8/21
 *  Email: emailtopan@163.com
 *
 */
interface ApiService {
    /**
     * 获取项目分类
     */
    @GET("/project/tree/json")
    fun getProjectType(): Observable<ResponseBean<List<ProjectBean>>>

    /**
     * 获取项目列表
     */
    @GET("/project/list/{page}/json")
    fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<PageBean<ProjectListBean>>
}