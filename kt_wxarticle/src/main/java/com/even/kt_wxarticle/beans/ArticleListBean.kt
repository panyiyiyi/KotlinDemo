package com.even.kt_wxarticle.beans

/**
 * @author  Created by Even on 2019/8/20
 *  Email: emailtopan@163.com
 *  公众号文章列表
 */
data class ArticleListBean(
    val title: String, //标题
    var niceDate: String,//日期
    val collect: Boolean,//收藏
    val desc: String, //描述
    val fresh: Boolean//是否最新
)