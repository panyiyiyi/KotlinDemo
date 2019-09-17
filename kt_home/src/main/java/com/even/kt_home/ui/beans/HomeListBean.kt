package com.even.kt_home.ui.beans

/**
 * @author  Created by Even on 2019/9/17
 *  Email: emailtopan@163.com
 *  首页文章列表
 */
data class HomeListBean(
    val author: String,
    val niceDate: String,
    val title: String,
    val chapterName: String,//二级分类
    val link: String,
    val superChapterName: String//一级分类
)