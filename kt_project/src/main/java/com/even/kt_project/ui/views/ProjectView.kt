package com.even.kt_project.ui.views

import com.even.common.base.BaseView
import com.even.common.base.model.Implement
import com.even.kt_project.beans.ProjectBean
import com.even.kt_project.beans.ProjectListBean
import com.even.kt_project.ui.presenters.ProjectPresenter

/**
 * @author  Created by Even on 2019/8/21
 *  Email: emailtopan@163.com
 *
 */
@Implement(ProjectPresenter::class)
interface ProjectView : BaseView {
    /**
     * 获取项目分类成功
     */
    fun getProjectSuccess(projectLists: List<ProjectBean>)

    /**
     * 获取项目列表
     */
    fun getProjectListSuccess(proLists: List<ProjectListBean>, pageNo: Int, pageTotal: Int)

    /**
     * 请求完成
     */
    fun reqOnComplete()
}