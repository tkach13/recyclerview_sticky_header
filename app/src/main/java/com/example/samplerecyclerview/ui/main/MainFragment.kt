package com.example.samplerecyclerview.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplerecyclerview.MovieDataModel
import com.example.samplerecyclerview.R
import com.tkach.RediAdapter.Adapter
import com.tkach.RediAdapter.StickyItemDecorator
import com.tkach.RediAdapter.models.ItemDrawer
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val viewManager = LinearLayoutManager(context)
    private val recyclerAdapter = Adapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val drawers=MovieDataModel.createList().groupBy {
            it.date
        }
        val newArrayList= ArrayList<ItemDrawer>()
        drawers.forEach {
            newArrayList.add(HeaderDrawer(getYear(it.key)))
            newArrayList.addAll(it.value.map {movieData->
                DataDrawer(movieData)
            })
        }

        recyclerAdapter.updateList(newArrayList)

        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = viewManager
            addItemDecoration(StickyItemDecorator(recyclerAdapter))
        }


    }

}
