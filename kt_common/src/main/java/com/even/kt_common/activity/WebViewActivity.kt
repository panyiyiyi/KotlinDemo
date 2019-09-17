package com.even.kt_common.activity

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.even.kt_common.R
import com.even.kt_common.base.BaseKtActivity
import com.even.kt_common.utils.CommonBundleKeys
import com.even.kt_common.utils.WebViewUtils

/**
 * @author  Created by Even on 2019/9/11
 *  Email: emailtopan@163.com
 *  webView 通用界面（只能查看）
 */
class WebViewActivity : BaseKtActivity() {
    private lateinit var mWebView: WebView

    override fun getContentView(): Int = R.layout.activity_webview

    override fun getLogicClazz(): Class<*>? = null

    override fun initView() {
        initTitleBar(intent.getStringExtra(CommonBundleKeys.KEY_TITLE), -1)
        val loadUrl = intent.getStringExtra(CommonBundleKeys.KEY_WEB_URL)

        mWebView = findViewById(R.id.webView)
        WebViewUtils.initWebView(mWebView)
        mWebView.webViewClient = WebClient()
        if (loadUrl.startsWith("https://") || loadUrl.startsWith("http://") || loadUrl.startsWith("file://")) {
            mWebView.loadUrl(loadUrl)
        } else {
            mWebView.loadDataWithBaseURL(null, loadUrl, "text/html", "utf-8", null)
        }
    }

    inner class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            activity.hideLoading()
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            activity.showLoading()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (mWebView.parent as ViewGroup).removeView(mWebView)

        mWebView.stopLoading()
        mWebView.clearHistory()
        mWebView.clearCache(true)
        mWebView.loadUrl("about:blank")
        mWebView.removeAllViews()
    }

    override fun finish() {
        if (mWebView.canGoBack()) {
            mWebView.goBack()
        } else {
            super.finish()
        }
    }
}