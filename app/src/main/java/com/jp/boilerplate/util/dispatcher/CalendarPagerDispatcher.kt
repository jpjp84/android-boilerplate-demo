package com.jp.boilerplate.util.dispatcher

import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger


fun RecyclerView.setScrollDispatcher(listener: CalendarPagerListener, pagerSnapHelper: PagerSnapHelper) {
    var scrolledPosition = -1

    addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            listener.onFirstPage()
            listener.onLastPage()
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
                listener.onFirstPage()
            }

            this@setScrollDispatcher.adapter?.let { adapter ->
                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrolledPosition == adapter.itemCount.minus(1)) {
                    listener.onLastPage()
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            scrolledPosition = pagerSnapHelper.findTargetSnapPosition(recyclerView.layoutManager, dx, dy)
        }
    })
}
