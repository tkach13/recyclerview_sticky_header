package com.example.samplerecyclerview.ui.main

import android.graphics.Movie
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplerecyclerview.MovieDataModel
import com.example.samplerecyclerview.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel


    private val viewManager = LinearLayoutManager(context)
    private val recyclerAdapter = MovieAdapter(MovieDataModel.generateNewList(MovieDataModel.createList()))


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        recyclerView.apply {
            layoutManager = viewManager
            adapter = recyclerAdapter
            addItemDecoration(MovieItemDecorator(recyclerAdapter))
        }

    }

}
