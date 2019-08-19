package com.even.kt_wxarticle.beans

/**
 * @author  Created by Even on 2019/8/19
 *  Email: emailtopan@163.com
 *  公众号Tab对象
 */
data class ArticleTabBean(
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Long,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int,
    val children: List<Any>
)