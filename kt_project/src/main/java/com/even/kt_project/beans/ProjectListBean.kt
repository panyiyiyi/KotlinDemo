package com.even.kt_project.beans

/**
 * @author  Created by Even on 2019/8/21
 *  Email: emailtopan@163.com
 *  项目列表返回对象
 */
data class ProjectListBean(
    val chapterName: String,//类别名称
    var collect: Boolean,//收藏
    val desc: String,//描述
    val envelopePic: String,//图片
    val link: String,//连接
    val niceDate: String,//更新日期
    val projectLink: String,//git地址
    val title: String,//标题
    val author: String//作者
)