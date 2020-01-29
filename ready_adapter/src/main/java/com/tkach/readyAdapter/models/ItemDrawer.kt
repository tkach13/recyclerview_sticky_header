package com.tkach.readyAdapter.models

import android.view.View
import androidx.annotation.LayoutRes

interface ItemDrawer {
    fun draw(view: View)
    fun isSticky(): Boolean
    @LayoutRes
    fun getLayoutId(): Int

}