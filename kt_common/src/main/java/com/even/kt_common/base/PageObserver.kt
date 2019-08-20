package com.even.kt_common.base

import com.even.common.request.observer.BaseObserver
import com.even.common.utils.ToastUtils
import io.reactivex.disposables.Disposable

/**
 * @author  Created by Even on 2019/8/20
 *  Email: emailtopan@163.com
 *
 */
abstract class PageObserver<T>(key: String) : BaseObserver<PageBean<T>>(key) {
    override fun doCompleted() {
    }

    override fun doFail(errorMsg: String) {
        ToastUtils.showShort(errorMsg)
    }

    override fun doNext(t: PageBean<T>) {
        if (t.errorCode == 0) {
            doSuccess(t.data.pageCount, t.data.datas)
        } else {
            doFail(t.errorMsg)
        }
    }

    override fun doSubscriber(disposable: Disposable) {
    }

    abstract fun doSuccess(pageTotal: Int, dataLists: List<T>)
}