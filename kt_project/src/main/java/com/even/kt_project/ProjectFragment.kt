package com.even.kt_project

import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.even.common.base.BaseFragment
import com.even.common.utils.ToastUtils
import com.even.common.utils.UiUtils
import com.even.commonrv.adapter.BaseRecyclerAdapter
import com.even.commonrv.adapter.BaseViewHolder
import com.even.commonrv.decoration.ItemDecorationWithMargin
import com.even.kt_common.utils.CommonOpenActivityHelper
import com.even.kt_project.beans.ProjectBean
import com.even.kt_project.beans.ProjectListBean
import com.even.kt_project.ui.presenters.ProjectPresenter
import com.even.kt_project.ui.views.ProjectView
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *  项目
 */
class ProjectFragment : BaseFragment(), ProjectView {


    //上一次选中的Item
    private var lastSelectPosition = 0
    private var currentId = 0
    private var pageNo = 1
    private var pageTotal = 1

    private lateinit var typeAdapter: BaseRecyclerAdapter<ProjectBean>
    private lateinit var contentAdapter: BaseRecyclerAdapter<ProjectListBean>
    private var typeLists = mutableListOf<ProjectBean>()
    private var contentLists = mutableListOf<ProjectListBean>()

    override fun getContentView(): Int = R.layout.fragment_project

    override fun getLogicClazz(): Class<*> = ProjectView::class.java

    override fun initView() {
        initRvType()
        initRvList()
        dlLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        srLayout.setOnRefreshListener {
            reqList(1, currentId)
        }
        srLayout.setOnLoadMoreListener {
            if (pageNo < pageTotal) {
                reqList(pageNo + 1, currentId)
            } else {
//                ToastUtils.showShort(getString(R.string.load_all))
                srLayout.finishLoadMoreWithNoMoreData()
            }

        }

    }

    private fun initRvType() {
        typeAdapter = object : BaseRecyclerAdapter<ProjectBean>(typeLists, R.layout.item_project_type) {
            override fun covert(holder: BaseViewHolder, item: ProjectBean, position: Int) {
                holder.setText(R.id.tvTitle, item.name)
                if (item.isSelect) {
                    holder.setViewBgColor(R.id.tvTitle, UiUtils.getColor(R.color.color26B7BC))
                    holder.setTextColor(R.id.tvTitle, UiUtils.getColor(R.color.colorFFFFFF))
                } else {
                    holder.setViewBgColor(R.id.tvTitle, UiUtils.getColor(R.color.colorFFFFFF))
                    holder.setTextColor(R.id.tvTitle, UiUtils.getColor(R.color.color000000))
                }
            }
        }
        typeAdapter.setOnItemClick { _, item, position ->
            if (lastSelectPosition != position) {
                //恢复上一个item显示
                typeLists[lastSelectPosition].isSelect = false
                item.isSelect = true
                typeAdapter.refreshItem(lastSelectPosition)
                typeAdapter.refreshItem(position)

                currentId = item.id
                reqList(1, item.id)
                lastSelectPosition = position

                //每换一个item都要清除
                contentLists.clear()
                dlLayout.closeDrawer(rvTitle)
            }
        }
        rvTitle.layoutManager = LinearLayoutManager(activity)
        rvTitle.adapter = typeAdapter
        rvTitle.addItemDecoration(ItemDecorationWithMargin().setMargin(0))
    }

    private fun reqList(pageNo: Int, id: Int) {
        (mPresenter as ProjectPresenter).getProjectList(pageNo, id)
    }

    private fun initRvList() {
        contentAdapter = object : BaseRecyclerAdapter<ProjectListBean>(contentLists, R.layout.item_project_list) {
            override fun covert(holder: BaseViewHolder, item: ProjectListBean?, position: Int) {
//                holder.setImageByUrl(R.id.civPhoto, item?.envelopePic, R.mipmap.ic_back)
//                Glide.with(activity).load(item?.envelopePic).into(holder.getView(R.id.civPhoto))
                holder.setText(R.id.tvTitle, item?.title)
                holder.setText(R.id.tvDes, item?.desc)
                holder.setText(
                    R.id.tvAuthor, String.format(getString(R.string.project_author, item?.author))
                )
                holder.setText(
                    R.id.tvTime, item?.niceDate
                )

            }

        }
        contentAdapter.setOnItemClick { _, item, _ ->
            CommonOpenActivityHelper.openWebViewActivity(activity, item.title, item.link)
        }
        rvContent.layoutManager = LinearLayoutManager(activity)
        rvContent.adapter = contentAdapter
        rvContent.addItemDecoration(ItemDecorationWithMargin().setMargin(0))
    }

    fun openMenu() {
        dlLayout.openDrawer(rvTitle)
    }

    override fun initData() {
        srLayout.post {
            srLayout.autoRefresh()
            (mPresenter as ProjectPresenter).getProjectType()
        }
    }

    override fun reqOnComplete() {
        srLayout.finishLoadMore()
        srLayout.finishRefresh()
    }


    override fun getProjectSuccess(projectLists: List<ProjectBean>) {
        typeLists.addAll(projectLists)
        typeLists[0].isSelect = true
        reqList(1, typeLists[0].id)
        currentId = typeLists[0].id
        typeAdapter.notifyDataSetChanged()
    }

    override fun getProjectListSuccess(proLists: List<ProjectListBean>, pageNo: Int, pageTotal: Int) {
        this.pageNo = pageNo
        this.pageTotal = pageTotal

        if (pageNo == 1) {
            contentLists.clear()
        }
        contentLists.addAll(proLists)
        contentAdapter.notifyDataSetChanged()
    }
}