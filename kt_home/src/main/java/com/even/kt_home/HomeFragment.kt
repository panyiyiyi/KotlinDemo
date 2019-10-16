package com.even.kt_home

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.even.common.base.BaseFragment
import com.even.common.utils.UiUtils
import com.even.commonrv.adapter.BaseRecyclerAdapter
import com.even.commonrv.adapter.BaseViewHolder
import com.even.commonrv.decoration.ItemDecorationWithMargin
import com.even.commonrv.utils.GlideUtil
import com.even.kt_common.utils.CommonOpenActivityHelper
import com.even.kt_home.ui.beans.BannerBean
import com.even.kt_home.ui.beans.HomeListBean
import com.even.kt_home.ui.presenters.HomePresenter
import com.even.kt_home.ui.views.HomeView
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author  Created by Even on 2019/8/15
 *  Email: emailtopan@163.com
 *  主页fragment
 */
class HomeFragment : BaseFragment(), HomeView {


    private var mPageNo = 1
    private var mPageTotal = 1

    private var mArticleLists = mutableListOf<HomeListBean>()
    private lateinit var mAdapter: BaseRecyclerAdapter<HomeListBean>


    override fun getContentView(): Int = R.layout.fragment_home

    override fun getLogicClazz(): Class<*> = HomeView::class.java

    override fun initView() {
        srLayout.setOnLoadMoreListener {
            if (mPageNo < mPageTotal) {
                reqList(mPageNo + 1)
            } else {
                srLayout.finishLoadMoreWithNoMoreData()
            }
        }
        srLayout.setOnRefreshListener { reqList(1) }

        mAdapter = object : BaseRecyclerAdapter<HomeListBean>(mArticleLists, R.layout.item_home_article) {
            override fun covert(holder: BaseViewHolder, item: HomeListBean, position: Int) {
                holder.setText(R.id.tvTitle, item.title)
                holder.setText(R.id.tvAuthor, String.format(UiUtils.getString(R.string.common_author), item.author))
                holder.setText(
                    R.id.tvClassify,
                    String.format(UiUtils.getString(R.string.home_classify), item.superChapterName, item.chapterName)
                )
                holder.setText(
                    R.id.tvTime,
                    String.format(UiUtils.getString(R.string.common_update_time), item.niceDate)
                )
            }
        }
        mAdapter.setOnItemClick { _, item, _ ->
            CommonOpenActivityHelper.openWebViewActivity(activity, item.title, item.link)
        }

        recyclerView.addItemDecoration(ItemDecorationWithMargin())
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter

        recyclerView.isScrollbarFadingEnabled = true
    }

    private fun reqList(pageNo: Int) {
        (mPresenter as HomePresenter).getHomeArticle(pageNo)
    }

    override fun initData() {
        (mPresenter as HomePresenter).getBanner()
        reqList(mPageNo)
    }


    override fun getHomeArticle(articleLists: List<HomeListBean>, pageNo: Int, totalPages: Int) {
        mPageNo = pageNo
        mPageTotal = totalPages
        if (pageNo == 1) {
            mArticleLists.clear()
        }
        mArticleLists.addAll(articleLists)
        mAdapter.notifyDataSetChanged()
    }

    override fun loadComplete() {
        srLayout.finishRefresh()
        srLayout.finishLoadMore()

    }

    override fun getBannerSuccess(bannerLists: MutableList<BannerBean>) {
        banner.setImages(bannerLists).setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context, bean: Any, imageView: ImageView) {
                GlideUtil.loadNet(imageView, (bean as BannerBean).imagePath)
            }
        }).setOnBannerListener { position ->
            CommonOpenActivityHelper.openWebViewActivity(
                activity,
                bannerLists[position].title,
                bannerLists[position].url
            )
        }.start()
    }
}