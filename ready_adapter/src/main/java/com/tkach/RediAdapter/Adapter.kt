package com.tkach.RediAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.tkach.RediAdapter.models.ItemDrawer


class Adapter : RecyclerView.Adapter<ViewHolder>(), StickyItemDecorator.StickyHeaderInterface {


    private var list = ArrayList<ItemDrawer>()

    fun updateList (list:ArrayList<ItemDrawer>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {

        var  itemPosition1 = itemPosition
        var  headerPosition = 0
        do
        {

            println("this.isHeader(itemPosition1) = ${this.isHeader(itemPosition1)}")
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
        return list[headerPosition].getLayoutId()
    }

    override fun bindHeaderData(header: View, headerPosition: Int) {
        list[headerPosition].apply {
            draw(header)
        }
    }



    override fun isHeader(itemPosition: Int): Boolean {
        return list[itemPosition].isSticky()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getLayoutId()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].apply {
            draw(holder.itemView)
        }
    }
}