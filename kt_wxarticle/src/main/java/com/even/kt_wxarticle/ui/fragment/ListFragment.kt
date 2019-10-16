package com.even.kt_wxarticle.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.even.common.base.BaseFragment
import com.even.common.request.observer.Transformer
import com.even.common.request.utils.RxHttpUtils
import com.even.common.utils.ToastUtils
import com.even.common.utils.UiUtils
import com.even.commonrv.adapter.BaseRecyclerAdapter
import com.even.commonrv.adapter.BaseViewHolder
import com.even.commonrv.decoration.ItemDecorationWithMargin
import com.even.kt_common.base.PageObserver
import com.even.kt_common.utils.CommonOpenActivityHelper
import com.even.kt_wxarticle.R
import com.even.kt_wxarticle.api.ApiService
import com.even.kt_wxarticle.beans.ArticleListBean
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * @author  Created by Even on 2019/8/20
 *  Email: emailtopan@163.com
 *  公众号列表fragment
 */
class ListFragment(private val articleId: String) : BaseFragment() {
    private var mPageNo = 1
    private var mPageTotal = 1

    var dataList = mutableListOf<ArticleListBean>()
    lateinit var adapter: BaseRecyclerAdapter<ArticleListBean>

    override fun getContentView(): Int = R.layout.fragment_list
    override fun getLogicClazz(): Class<*>? = null
    override fun initView() {
        srLayout.setOnLoadMoreListener {
            if (mPageNo < mPageTotal) {
                getData(++mPageNo)
            } else {
                ToastUtils.showShort(UiUtils.getString(R.string.load_all))
                srLayout.finishLoadMoreWithNoMoreData()
            }
        }
        srLayout.setOnRefreshListener {
            getData(1)
        }

        adapter = object : BaseRecyclerAdapter<ArticleListBean>(dataList, R.layout.item_article_list) {
            override fun covert(holder: BaseViewHolder?, item: ArticleListBean?, position: Int) {
                holder?.setText(R.id.tvTitle, item?.title)
                holder?.setText(R.id.tvTime, String.format(UiUtils.getString(R.string.wt_update_time), item?.niceDate))
            }
        }
        adapter.setOnItemClick { _, item, _ ->
            CommonOpenActivityHelper.openWebViewActivity(activity, item.title, item.link)

        }
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemDecorationWithMargin().setMargin(0))
        recyclerView.layoutManager = LinearLayoutManager(activity)

    }

    private fun getData(page: Int) {
        RxHttpUtils.createApi(ApiService::class.java)
            .getArticleLists(articleId, page)
            .compose(Transformer.switchSchedulers())
            .subscribe(object : PageObserver<ArticleListBean>(activity.mRxTag) {
                override fun doSuccess(pageTotal: Int, dataLists: List<ArticleListBean>) {
                    mPageTotal = pageTotal
                    dataList.addAll(dataLists)
                    adapter.notifyDataSetChanged()
                    mPageNo = page
                }

                override fun doCompleted() {
                    super.doCompleted()
                    srLayout?.finishRefresh()
                    srLayout?.finishLoadMore()
                }
            })
    }

    override fun initData() {
        srLayout.post {
            srLayout.autoRefresh()
            getData(mPageNo)
        }
    }
}