package com.hankkin.jetpack_note.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar

/**
 * created by ${Hankkin}
 * on 2019-06-12
 */

fun Fragment.snackBarShow(view: View, str: String) {
    Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.clipTxt(txt: String) {
    val cm = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val mClipData = ClipData.newPlainText("Label", txt)
//    cm.primaryClip = mClipData
}

fun Fragment.toast(message: Any) {
    activity?.let {
        Toast.makeText(it, "$message", Toast.LENGTH_SHORT).show()
    }
}

//fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
//    ViewModelProviders.of(this, activity?.application?.let { ViewModelFactory.getInstance(it) }).get(viewModelClass)
fun Fragment.getDrawable(@DrawableRes id: Int): Drawable? {
    return activity?.let { ContextCompat.getDrawable(it, id) }
}