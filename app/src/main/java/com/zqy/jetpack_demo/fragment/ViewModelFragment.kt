package com.zqy.jetpack_demo.fragment

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.ChangeClipBounds
import androidx.transition.TransitionManager
import com.hankkin.jetpack_note.ext.toast
import com.zqy.jetpack_demo.R
import com.zqy.jetpack_demo.databinding.ViewModleFragmentBinding
import com.zqy.jetpack_demo.viewmodel.ThisViewModel
import kotlinx.android.synthetic.main.view_modle_fragment.*


class ViewModelFragment : Fragment() {
    private lateinit var binding: ViewModleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewModleFragmentBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.view_modle_fragment, null)
    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSource = ViewModelProviders.of(this).get(ThisViewModel::class.java)
        show(clipLayout)
        clickAdd.setOnClickListener {
            //            dataSource.addData()
            TransitionManager.beginDelayedTransition(clipLayout, ChangeClipBounds())
            val clipBounds = clipLayout.clipBounds
            val r = Rect(
                clipLayout.left,
                clipLayout.top,
                clipLayout.right + 150,
                clipLayout.bottom + 150
            )
            if (r == clipBounds) {
                clipLayout.clipBounds = null
            } else {
                clipLayout.clipBounds = r
            }
            show(clipLayout)

        }

        activity?.let {
            dataSource.dataBean.observe(it, Observer { it1 ->
                toast(it1)
                textHHH.text = "$it1"
            })
        }
    }

    fun show(viewGroup: ViewGroup) {
        showPath.text = ""
        showPath.append("Top：${viewGroup.top}   ")
        showPath.append("Bottom：${viewGroup.bottom} ")
        showPath.append("left：${viewGroup.left} ")
        showPath.append("right：${viewGroup.right}   ")
    }
}