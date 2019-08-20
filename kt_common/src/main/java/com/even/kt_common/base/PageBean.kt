package com.even.kt_common.base

/**
 * @author  Created by Even on 2019/8/20
 *  Email: emailtopan@163.com
 *  分页基类对象
 */
data class PageBean<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: PageDataBean<T>
)

class PageDataBean<T>(
    val curPage: Int,
    val pageCount: Int,
    val datas: MutableList<T>
)