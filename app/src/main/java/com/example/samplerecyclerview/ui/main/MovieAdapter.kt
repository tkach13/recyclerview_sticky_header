package com.example.samplerecyclerview.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplerecyclerview.MovieDataModel
import com.example.samplerecyclerview.MovieDataType
import com.example.samplerecyclerview.R
import kotlinx.android.synthetic.main.item_movies.view.*
import kotlinx.android.synthetic.main.item_sticky_header.view.*

class MovieAdapter(private val items: ArrayList<MovieDataModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>(), MovieItemDecorator.StickyHeaderInterface {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemViewMovie = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        val itemViewHeader = LayoutInflater.from(parent.context).inflate(R.layout.item_sticky_header, parent, false)
        return when (viewType) {
            0 -> MovieViewHolder(itemViewMovie)
            else -> HeaderViewHolder(itemViewHeader)
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> holder.itemView.tvTitle.text = items[position].title
            1 -> holder.itemView.tvHeaderTitle.text = getYear(items[position].date).toString()
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return when (items[position].dataType) {
            MovieDataType.HEADER_TYPE -> 1
            MovieDataType.MOVIE_TYPE -> 0

        }
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {

        //todo this method always returns 0 no matter what position, needs fixing

        var  itemPosition1 = itemPosition
        var  headerPosition = 0
            do
            {
                if (this.isHeader(itemPosition1)) {
                    headerPosition = itemPosition1
                    break
                }
                itemPosition1 -= 1
            }
            while (itemPosition1 >= 0)

            return headerPosition

    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return  R.layout.item_sticky_header

    }

    override fun bindHeaderData(header: View, headerPosition: Int) {
          val   tvHeader = header.tvHeaderTitle
            tvHeader.text = getYear(items[headerPosition].date).toString()
    }

    override fun isHeader(itemPosition: Int): Boolean {
        return (items[itemPosition].dataType == MovieDataType.HEADER_TYPE)


    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}