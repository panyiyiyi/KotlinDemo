package com.even.kt_common.utils

import android.webkit.WebSettings
import android.webkit.WebView

/**
 * @author  Created by Even on 2019/9/11
 *  Email: emailtopan@163.com
 *
 */
object WebViewUtils {

    /**
     * 初始化webView
     */
    fun initWebView(webView: WebView) {
        val settings = webView.settings
        //设置自适应屏幕
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        //将图片调整到适合webview大小
        settings.useWideViewPort = true
        //缩放屏幕大小
        settings.loadWithOverviewMode = true

        //禁止缩放
        settings.setSupportZoom(false)
        settings.javaScriptEnabled = true

        //设置可以访问文件
        settings.allowFileAccess = true
        //设置自动加载图片
        settings.loadsImagesAutomatically = true
        //设置编码格式
        settings.defaultTextEncodingName = "utf-8"
        settings.domStorageEnabled = true
    }
}