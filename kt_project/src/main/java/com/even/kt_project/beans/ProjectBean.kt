package com.even.kt_project.beans

/**
 * @author  Created by Even on 2019/8/21
 *  Email: emailtopan@163.com
 *  项目返回对象
 */
data class ProjectBean(
    val id: Int,
    val name: String,
    val order: String,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)