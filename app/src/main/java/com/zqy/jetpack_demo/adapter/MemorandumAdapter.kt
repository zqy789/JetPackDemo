package com.zqy.jetpack_demo.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zqy.jetpack_demo.R
import com.zqy.jetpack_demo.entity.Memorandum
import com.zqy.jetpack_demo.utils.TimeUtil

class MemorandumAdapter(
    list: MutableList<Memorandum>,
    id: Int
) : BaseQuickAdapter<Memorandum, BaseViewHolder>(id, list) {
    override fun convert(helper: BaseViewHolder, item: Memorandum?) {
        item?.let {
            helper.addOnClickListener(R.id.note_item_layout)
                .addOnClickListener(R.id.room_item_delete)
                .setText(R.id.room_item_title, it.title)
                .setText(R.id.room_item_time, TimeUtil.getTime(it.updateTime))
                .setText(R.id.room_item_content, it.content)

        }
    }
}