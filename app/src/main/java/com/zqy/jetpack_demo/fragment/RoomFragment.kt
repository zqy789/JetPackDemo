package com.zqy.jetpack_demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hankkin.jetpack_note.ext.getDrawable
import com.zhan.mvp.ext.logd
import com.zqy.jetpack_demo.R
import com.zqy.jetpack_demo.adapter.MemorandumAdapter
import com.zqy.jetpack_demo.database.AppDatabase
import com.zqy.jetpack_demo.entity.Memorandum
import com.zqy.jetpack_demo.ext.setMyEmptyView
import com.zqy.jetpack_demo.utils.pop.FilterPopWindow
import com.zqy.jetpack_demo.utils.pop.PopClickImpl
import com.zqy.jetpack_demo.utils.pop.PopStatus
import kotlinx.android.synthetic.main.room_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 数据库
 */
class RoomFragment : Fragment(), BaseQuickAdapter.OnItemChildClickListener,
    PopClickImpl {


    override fun itemClick(position: Int) {
        when (position) {
            PopStatus.创建时间 -> {
            }
            PopStatus.编辑日期 -> {
            }
            PopStatus.标题 -> {
            }
        }
    }

    private val TAG = "RoomFragment"

    private var list: MutableList<Memorandum>? = null

    private var isLine = true

    private val dao by lazy {
        activity?.let { AppDatabase.getInstance(it).memorandumDao() }
    }
    private val popupWindow by lazy {
        activity?.let { FilterPopWindow(it, this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.room_fragment, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logd("我更新数据了", TAG)
        //查询数据库内容
        activity?.let {
            list = mutableListOf()

            val (adapter, viewInflate) = withAdapter(it)
            //数据库中查询所有日记
            dao?.getAll()?.observe(it, Observer { data ->
                list!!.clear()
                list!!.addAll(data)
                adapter.notifyDataSetChanged()
            })
            viewInflate.findViewById<Button>(R.id.clickWrite).setOnClickListener { view ->
                view.findNavController().navigate(R.id.noteFragment)
            }
        }

        //创建新的备忘录
        addNotes.setOnClickListener {
            it.findNavController().navigate(R.id.noteFragment)
        }
        //切换布局
        changeLayout.setOnClickListener {
            isLine = !isLine
            diaryList.layoutManager = layoutManager()
            it.setBackgroundResource(getRecourse())
        }

        //弹出切换项
        changeFilter.setOnClickListener {
            popupWindow?.show(it)
        }
    }


    /**
     * todo Pair 能够携带多个返回值
     */
    private fun withAdapter(it: FragmentActivity): Pair<MemorandumAdapter, View> {
        val adapter = MemorandumAdapter(list!!, R.layout.room_list_item)
        //设置空布局
        val viewInflate = adapter.setMyEmptyView(it)
        diaryList.adapter = adapter
        //添加分割线
        val dividerItemDecoration =
            DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
        val draw = getDrawable(R.drawable.shape_item_divder)
        dividerItemDecoration.setDrawable(draw!!)
        diaryList.addItemDecoration(dividerItemDecoration)
        adapter.onItemChildClickListener = this
        return Pair(adapter, viewInflate)
    }

    private fun layoutManager() =
        if (isLine) LinearLayoutManager(activity) else GridLayoutManager(activity, 2)

    private fun getRecourse() =
        if (isLine) R.drawable.ic_apps_black_24dp_no_check else R.drawable.ic_apps_black_24dp


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val memorandum = list!![position]

        when (view?.id) {
            R.id.room_item_delete -> {
                toDelete(memorandum)
                adapter?.notifyItemRemoved(position)
            }
            else -> {
                //进入备忘录
                if (view != null) {
                    Navigation.findNavController(view).navigate(R.id.noteFragment, Bundle().apply {
                        putParcelable(ARGUMENT_NAME, memorandum)
                    })
                }
            }
        }
    }

    private fun toDelete(
        memorandum: Memorandum
    ) {
        //打开线程删除
        GlobalScope.launch {
            dao?.deleteWhereId(memorandum)
        }
    }

    companion object {
        const val ARGUMENT_NAME = "note_bean"
    }
}