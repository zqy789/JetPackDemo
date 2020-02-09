package com.zqy.jetpack_demo.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zqy.jetpack_demo.activity.MemorandumActivity
import com.zqy.jetpack_demo.activity.NavigationActivity
import com.zqy.jetpack_demo.R
import com.zqy.jetpack_demo.adapter.HomeListAdapter
import com.zqy.jetpack_demo.adapter.ItemClick
import com.zqy.jetpack_demo.databinding.HomeFragmentBinding
import com.zqy.jetpack_demo.entity.HomeListEntity
import com.zqy.jetpack_demo.utils.JsonReadUtils


class HomeFragment : Fragment(), ItemClick {


    private var isLine: Boolean = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = HomeListAdapter(this)
        //适配器
        binding.homeRecycler.adapter = adapter
        setData(binding, adapter)
        return binding.root
    }

    /**
     * 使用LiveDate接收数据
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun setData(binding: HomeFragmentBinding?, adapter: HomeListAdapter) {
        activity?.let {
            val json = JsonReadUtils.getInstance().get(it, R.raw.home)
            val fromJson =
                Gson().fromJson<MutableList<HomeListEntity>>(
                    json,
                    object : TypeToken<MutableList<HomeListEntity>>() {}.type
                )
            adapter.submitList(fromJson)
        }

        binding?.fab?.setOnClickListener {
            isLine = !isLine
            binding.homeRecycler.layoutManager = layoutManager()
            binding.fab.setImageResource(getRecourse())
        }
    }

    override fun click(adapter: HomeListAdapter, position: Int, view: View) {
        try {

            val findNavController = Navigation.findNavController(view)

            when (position) {
                0 -> {
                    startActivity(Intent(activity, NavigationActivity::class.java))
                }
                4 -> {
                    findNavController.navigate(R.id.viewModleFragment)
                }
                5 -> {
                    //paging
                    findNavController.navigate(R.id.pagingFragment)
                }
                6 -> {
                    startActivity(Intent(activity, MemorandumActivity::class.java))
                }
            }
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            println("当前的不存在")
        }
    }

    private fun layoutManager() =
        if (isLine) LinearLayoutManager(activity) else GridLayoutManager(activity, 2)

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getRecourse() =
        if (!isLine) R.mipmap.icon_lin else R.mipmap.icon_grid


}