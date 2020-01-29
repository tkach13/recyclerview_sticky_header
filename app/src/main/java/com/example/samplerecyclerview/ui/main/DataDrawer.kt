package com.example.samplerecyclerview.ui.main

import android.view.View
import com.example.samplerecyclerview.MovieDataModel
import com.example.samplerecyclerview.R
import com.tkach.readyAdapter.models.ItemDrawer
import kotlinx.android.synthetic.main.item_movies.view.*


class DataDrawer(private val movieDataModel: MovieDataModel): ItemDrawer {
    override fun draw(view: View) {
        view.tvTitle.text = movieDataModel.title
    }

    override fun isSticky(): Boolean {
            return false
    }

    override fun getLayoutId(): Int {
        return  R.layout.item_movies
    }
}