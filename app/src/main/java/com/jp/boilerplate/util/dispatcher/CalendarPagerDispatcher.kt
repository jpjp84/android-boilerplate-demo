package com.jp.boilerplate.util.dispatcher

import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.setScrollDispatcher(listener: CalendarPagerListener, pagerSnapHelper: PagerSnapHelper) {
    var scrolledPosition = -1

    addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            listener.willUpdateEdgePage(CalendarPagerListener.EdgePageState.ALL)
            removeOnChildAttachStateChangeListener(this)
        }

        override fun onChildViewDetachedFromWindow(view: View) {}
    })
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                listener.onChangePage(scrolledPosition)
            }

            if (newState == RecyclerView.SCROLL_STATE_IDLE && scrolledPosition == 0) {
                listener.willUpdateEdgePage(CalendarPagerListener.EdgePageState.FIRST)
            }

            this@setScrollDispatcher.adapter?.let { adapter ->
                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrolledPosition == adapter.itemCount.minus(1)) {
                    listener.willUpdateEdgePage(CalendarPagerListener.EdgePageState.LAST)
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            scrolledPosition = pagerSnapHelper.findTargetSnapPosition(recyclerView.layoutManager, dx, dy)
        }
    })
}
