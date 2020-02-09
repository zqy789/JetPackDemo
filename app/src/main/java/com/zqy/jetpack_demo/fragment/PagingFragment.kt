package com.zqy.jetpack_demo.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zqy.jetpack_demo.R
import com.zqy.jetpack_demo.adapter.PagingSampleAdapter
import com.zqy.jetpack_demo.entity.PagingSampleEntity
import com.zqy.jetpack_demo.paging.PagingFactory
import com.zqy.jetpack_demo.paging.PagingHelper
import com.zqy.jetpack_demo.respository.PagingRepository
import kotlinx.android.synthetic.main.fragment_paging.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * @author zhangqiuyang
 */
class PagingFragment : Fragment() {

    private var pagingData: PagedList<PagingSampleEntity>? = null

    private val adapter by lazy {
        PagingSampleAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_paging, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagingList.apply {
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            adapter = this@PagingFragment.adapter
        }

        pagingSwipe.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.color_8BC34A,
            R.color.color_red
        )

        pagingSwipe.setOnRefreshListener {
            adapter.submitList(pagingData)
            pagingSwipe.isRefreshing = false
        }
        activity?.let {
            pagingSwipe.isRefreshing = true

            PagingHelper.init(PagingFactory()).observe(it, Observer { list ->
                this.pagingData = list
                adapter.submitList(list)
                pagingSwipe.isRefreshing = false
            })
        }
    }

}
