package com.even.kt_common.utils

import android.app.Activity
import android.content.Intent
import com.even.kt_common.activity.WebViewActivity

/**
 * @author  Created by Even on 2019/9/11
 *  Email: emailtopan@163.com
 *   activity跳转工具类
 */
object CommonOpenActivityHelper {
    /**
     * 开启webView界面
     * @param title 标题
     * @param loadUrl url
     */
    fun openWebViewActivity(activity: Activity, title: String, loadUrl: String) {
        val intent = Intent(activity, WebViewActivity::class.java)
        intent.putExtra(CommonBundleKeys.KEY_TITLE, title)
        intent.putExtra(CommonBundleKeys.KEY_WEB_URL, loadUrl)
        activity.startActivity(intent)
    }

}