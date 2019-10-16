package com.even.kt_project.ui.presenters

import com.even.common.base.BasePresenter
import com.even.common.request.observer.Transformer
import com.even.common.request.utils.RxHttpUtils
import com.even.kt_common.base.PageObserver
import com.even.kt_common.base.ResponseObserver
import com.even.kt_project.api.ApiService
import com.even.kt_project.beans.ProjectBean
import com.even.kt_project.beans.ProjectListBean
import com.even.kt_project.ui.views.ProjectView

/**
 * @author  Created by Even on 2019/8/21
 *  Email: emailtopan@163.com
 *
 */
class ProjectPresenter : BasePresenter<ProjectView>() {
    /**
     * 获取项目分类
     */
    fun getProjectType() {
        RxHttpUtils.createApi(ApiService::class.java)
            .getProjectType()
            .compose(Transformer.switchSchedulers())
            .subscribe(object : ResponseObserver<List<ProjectBean>>(RxTag) {
                override fun doSuccess(t: List<ProjectBean>) {
                    getView()?.getProjectSuccess(t)
                }
            })
    }

    /**
     * 获取项目列表
     */
    fun getProjectList(pageNo: Int, cid: Int) {
        RxHttpUtils.createApi(ApiService::class.java)
            .getProjectList(pageNo, cid)
            .compose(Transformer.switchSchedulers())
            .subscribe(object : PageObserver<ProjectListBean>(RxTag) {
                override fun doSuccess(pageTotal: Int, dataLists: List<ProjectListBean>) {
                    getView()?.getProjectListSuccess(dataLists, pageNo, pageTotal)
                }

                override fun onComplete() {
                    super.onComplete()
                    getView()?.reqOnComplete()
                }
            })

    }
}