package com.even.kt_common.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.even.common.base.BaseActivity
import com.even.kt_common.R
import kotlinx.android.synthetic.main.item_title_bar.*

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
abstract class BaseKtActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorFFFFFF)
        }
    }

    override fun getTitleBarView(): Int = R.layout.item_title_bar

    fun initTitleBar(title: String, rightText: String) {
        initTitleBar(title, rightText, -1)
    }

    fun initTitleBar(title: String, rightImage: Int) {
        initTitleBar(title, "", rightImage)
    }

    /**
     * 设置标题数据
     */
    private fun initTitleBar(title: String, rightText: String, rightImage: Int) {
        tvTitle.text = title
        if (rightText.isNotEmpty()) {
            tvRight.text = rightText
            tvRight.visibility = View.VISIBLE
        }
        if (rightImage != -1) {
            ivRight.setImageResource(rightImage)
            ivRight.visibility = View.VISIBLE
        }
        ivBack.setOnClickListener { finish() }
    }
}