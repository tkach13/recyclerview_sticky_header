package com.example.samplerecyclerview.ui.main

import android.view.View
import com.example.samplerecyclerview.R
import com.tkach.RediAdapter.models.ItemDrawer
import kotlinx.android.synthetic.main.item_sticky_header.view.*

class HeaderDrawer(private val year:Int?):ItemDrawer{
    override fun draw(view: View) {
        view.tvHeaderTitle.text = year.toString()
    }

    override fun isSticky(): Boolean {
            return true
            }

    override fun getLayoutId(): Int {
        return R.layout.item_sticky_header
    }
}