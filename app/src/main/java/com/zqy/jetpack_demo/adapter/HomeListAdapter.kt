package com.zqy.jetpack_demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zqy.jetpack_demo.databinding.ListItemBinding
import com.zqy.jetpack_demo.entity.HomeListEntity


class HomeListAdapter(
    private val item: ItemClick
) : ListAdapter<HomeListEntity, HomeListAdapter.VH>(ComponentDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        //设置布局
        return VH(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val itemBean = getItem(position)
        println("onBindViewHolder${itemBean.title}")
        holder.bind(View.OnClickListener {
            //条目点击事件
            println("onBindViewHolder-->我点击了")
            item.click(this, position,it)
        }, itemBean)
    }


    /**
     * 这里对应item 布局
     */
    class VH(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(listener: View.OnClickListener, item: HomeListEntity) {
            binding.apply {
                clickListener = listener
                bean = item
                executePendingBindings()
            }
        }
    }
}

interface ItemClick {
    fun click(adapter: HomeListAdapter, position: Int,view:View)
}

private class ComponentDiffCallback : DiffUtil.ItemCallback<HomeListEntity>() {
    override fun areItemsTheSame(oldItem: HomeListEntity, newItem: HomeListEntity): Boolean {
        return oldItem.link == newItem.link
    }

    override fun areContentsTheSame(oldItem: HomeListEntity, newItem: HomeListEntity): Boolean {
        return oldItem == newItem
    }

}