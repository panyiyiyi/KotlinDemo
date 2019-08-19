package com.even.kt_common.base

/**
 * @author  Created by Even on 2019/8/19
 *  Email: emailtopan@163.com
 *  返回对象基类
 */
data class BaseResponseBean<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)