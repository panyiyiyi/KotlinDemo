package com.even.kt_project

import androidx.recyclerview.widget.LinearLayoutManager
import com.even.common.base.BaseFragment
import com.even.commonrv.adapter.BaseRecyclerAdapter
import com.even.commonrv.adapter.BaseViewHolder
import com.even.commonrv.decoration.ItemDecorationWithMargin
import com.even.commonrv.impl.OnItemClickListener
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


    private lateinit var typeAdapter: BaseRecyclerAdapter<ProjectBean>
    private lateinit var contentAdapter: BaseRecyclerAdapter<ProjectListBean>
    private var typeLists = mutableListOf<ProjectBean>()
    private var contentLists = mutableListOf<ProjectListBean>()

    override fun getContentView(): Int = R.layout.fragment_project

    override fun getLogicClazz(): Class<*> = ProjectView::class.java

    override fun initView() {
        initRvType()
        initRvList()
    }

    private fun initRvType() {
        typeAdapter = object : BaseRecyclerAdapter<ProjectBean>(typeLists, R.layout.item_project_type) {
            override fun covert(holder: BaseViewHolder, item: ProjectBean?, position: Int) {
                holder.setText(R.id.tvTitle, item?.name)
            }
        }
        typeAdapter.setOnItemClick { _, item, _ ->
            (mPresenter as ProjectPresenter).getProjectList(item.id)
        }
        rvTitle.layoutManager = LinearLayoutManager(activity)
        rvTitle.adapter = typeAdapter
        rvTitle.addItemDecoration(ItemDecorationWithMargin().setMargin(0))
    }

    private fun initRvList() {
        contentAdapter = object : BaseRecyclerAdapter<ProjectListBean>(contentLists, R.layout.item_project_list) {
            override fun covert(holder: BaseViewHolder, item: ProjectListBean?, position: Int) {
//                holder.setImageByUrl(R.id.civPhoto, item?.envelopePic, R.mipmap.ic_back)
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
        contentAdapter.setOnItemClick { holder, item, position ->
        }
        rvContent.layoutManager = LinearLayoutManager(activity)
        rvContent.adapter = contentAdapter
        rvContent.addItemDecoration(ItemDecorationWithMargin().setMargin(0))
    }


    override fun initData() {
        (mPresenter as ProjectPresenter).getProjectType()
    }

    override fun getProjectSuccess(projectLists: List<ProjectBean>) {
        typeLists.addAll(projectLists)
        typeAdapter.notifyDataSetChanged()
    }

    override fun getProjectListSuccess(proLists: List<ProjectListBean>, pageTotal: Int) {
        contentLists.addAll(proLists)
        contentAdapter.notifyDataSetChanged()
    }
}