package com.tkach.readyAdapter


import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class StickyItemDecorator(@NonNull listener: StickyHeaderInterface) : RecyclerView.ItemDecoration() {

    private var mStickyHeaderHeight:Int = 0
    private var mlistener: StickyHeaderInterface = listener

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val topChild = parent.getChildAt(0) ?: return
        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if (topChildPosition == RecyclerView.NO_POSITION) {
            return
        }
        val headerPosition = mlistener.getHeaderPositionForItem(topChildPosition)
        val currentHeader = getHeaderViewForItem(headerPosition, parent)
        fixLayoutSize(parent, currentHeader)
        val contactPoint = currentHeader.bottom
        val childInContact  = getChildInContact(parent,contactPoint,headerPosition)
        if (childInContact != null  && mlistener.isHeader(parent.getChildAdapterPosition(childInContact))){
            moveHeader(c,currentHeader,childInContact)
            return
        }

        drawHeader(c, currentHeader)

    }

    private fun drawHeader(c: Canvas, currentHeader: View) {
        c.save()
        c.translate(0f,0f)
        currentHeader.draw(c)
        c.restore()
    }

    private fun moveHeader(c: Canvas, currentHeader: View, childInContact: View) {
        c.save()
        c.translate(0f,(childInContact.top - currentHeader.height).toFloat())
        currentHeader.draw(c)
        c.restore()
    }

    private fun getChildInContact(parent: RecyclerView, contactPoint: Int, headerPosition: Int): View ?{
        var childInContact:View? = null

        for (i in 0 until parent.childCount){
            println("parentCount = ${parent.childCount}")
            var heightTolerance:Int = 0
            var child = parent.getChildAt(i)

            if (headerPosition!= i ){
                val isChildHeader = mlistener.isHeader(parent.getChildAdapterPosition(child))
                if (isChildHeader){
                    heightTolerance = mStickyHeaderHeight - child.height
                }


            }
            var childBottomPosition:Int = 0
            if (child.top>0){
                childBottomPosition = child.bottom + heightTolerance
            } else {
                childBottomPosition = child.bottom
            }
            if (childBottomPosition>contactPoint){
                if (child.top<=contactPoint){
                    childInContact = child
                    break
                }
            }

        }
        return childInContact!!
    }

    private fun fixLayoutSize(parent: RecyclerView, view: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        val childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.paddingLeft + parent.paddingRight,
                view.layoutParams.width)
        val childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,parent.paddingTop + parent.paddingBottom,
                view.layoutParams.height)

        view.measure(childWidthSpec,childHeightSpec)
        mStickyHeaderHeight = view.measuredHeight

        view.layout(0,0,view.measuredWidth,mStickyHeaderHeight)
    }

    private fun getHeaderViewForItem(headerPosition: Int, parent: RecyclerView): View {
        val layoutResId = mlistener.getHeaderLayout(headerPosition)
        val header = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        mlistener.bindHeaderData(header, headerPosition)
        return header

    }


    interface StickyHeaderInterface {

        fun getHeaderPositionForItem(itemPosition:Int):Int

        fun getHeaderLayout(headerPosition:Int):Int

        fun bindHeaderData(header: View, headerPosition:Int)

        fun isHeader(itemPosition:Int):Boolean

    }
}

