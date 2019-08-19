package com.even.kt_common.base

import com.even.common.request.observer.BaseObserver
import com.even.common.utils.ToastUtils
import io.reactivex.disposables.Disposable

/**
 * @author  Created by Even on 2019/8/19
 *  Email: emailtopan@163.com
 *  返回解析对象
 */
abstract class ResponseObserver<T>(key: String) : BaseObserver<BaseResponseBean<T>>(key) {
    override fun doCompleted() {
    }

    override fun doFail(errorMsg: String) {
        ToastUtils.showShort(errorMsg)
    }

    override fun doNext(t: BaseResponseBean<T>) {
        if (t.errorCode == 0) {
            doSuccess(t.data)
        } else {
            doFail(t.errorMsg)
        }
    }

    override fun doSubscriber(disposable: Disposable) {
    }

    /**
     * 成功
     */
    abstract fun doSuccess(t: T)
}