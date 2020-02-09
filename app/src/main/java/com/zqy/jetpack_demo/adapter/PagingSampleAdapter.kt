package com.zqy.jetpack_demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zqy.jetpack_demo.R
import com.zqy.jetpack_demo.entity.PagingSampleEntity
import com.zqy.jetpack_demo.net.BasePagingEntity

class PagingSampleAdapter :
    PagedListAdapter<PagingSampleEntity, PagingSampleAdapter.PagingSampleViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingSampleViewHolder {
        return PagingSampleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.paging_list_item, null)
        )
    }

    override fun onBindViewHolder(holder: PagingSampleViewHolder, position: Int) {
        val item = getItem(position)
        holder.authorname.text = item?.chapterName
        holder.name.text = item?.author
    }


    class PagingSampleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val authorname: TextView = view.findViewById(R.id.authorName)
        val name: TextView = view.findViewById(R.id.pagingName)
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PagingSampleEntity>() {


            override fun areItemsTheSame(
                oldItem: PagingSampleEntity,
                newItem: PagingSampleEntity
            ): Boolean {
                return oldItem.userId == newItem.userId
            }

            override fun areContentsTheSame(
                oldItem: PagingSampleEntity,
                newItem: PagingSampleEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}