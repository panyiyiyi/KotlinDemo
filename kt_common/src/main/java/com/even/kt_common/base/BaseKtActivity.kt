package com.even.kt_common.base

import android.view.View
import com.even.common.base.BaseActivity
import com.even.kt_common.R
import kotlinx.android.synthetic.main.item_title_bar.*

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
abstract class BaseKtActivity : BaseActivity() {
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