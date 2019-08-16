package com.even.kt_project

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.even.common.base.BaseLazyFragment
import com.even.kt_common.router.ARouterPath
import com.even.kt_common.router.provider.IProjectProvider

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *
 */
@Route(path = ARouterPath.PROJECT_SERVICE_PATH, name = "项目")
class ProjectService : IProjectProvider {
    override fun getProjectFragment(): BaseLazyFragment = ProjectFragment()

    override fun init(context: Context?) {
    }
}