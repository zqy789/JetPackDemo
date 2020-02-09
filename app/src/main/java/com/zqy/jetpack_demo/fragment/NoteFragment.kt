package com.zqy.jetpack_demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hankkin.jetpack_note.ext.toast
import com.zhan.mvp.ext.logd
import com.zqy.jetpack_demo.R
import com.zqy.jetpack_demo.database.AppDatabase
import com.zqy.jetpack_demo.entity.Memorandum
import com.zqy.jetpack_demo.utils.TimeUtil
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    private val memorandum: Memorandum?
        get() {
            return arguments?.getParcelable(RoomFragment.ARGUMENT_NAME)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //获取序列化对象
        memorandum

        if (!argIsNull()) {
            editTitle.setText(memorandum?.title)
            editContent.setText(memorandum?.content)
            noteStubTime.inflate()
            logd("${memorandum?.id}", "argIsNull")
            activity?.findViewById<TextView>(R.id.stub_time_text)?.text =
                memorandum?.updateTime?.let {
                    TimeUtil.getTime(it)
                }
        }
    }

    private fun argIsNull() = null == memorandum

    override fun onStop() {
        super.onStop()
        //保存当前的内容
        saveDataToDb()
    }

    private fun saveDataToDb() {
        val title = editTitle.text.trim().toString()
        val content = editContent.text.trim().toString()

        if (title.isEmpty()) {
            return toast("标题为空，保存失败")
        }
        if (content.isEmpty()) {
            return toast("内容为空，保存失败")
        }

        activity?.let {
            val thisTime = TimeUtil.getTime()
            //设置主键
            val id = if (argIsNull())
                thisTime
            else
                memorandum?.updateTime

            logd("主键是${id}time是${thisTime}", "saveDataToDb")
            //设置实体类
            val bean = Memorandum(
                id!!,
                title,
                thisTime,
                content
            )
            //实例化db
            val memorandumDao = AppDatabase.getInstance(it).memorandumDao()
            //开启线程
            GlobalScope.launch {
                if (argIsNull()) {
                    memorandumDao.insert(bean)
                } else {
                    memorandumDao.updateWhereId(bean)
                }
            }
        }
    }

}