package com.jp.boilerplate.util.dispatcher

interface CalendarPagerListener {

    fun onFirstPage() {
        willUpdateEdgePage(true)
    }

    fun onLastPage() {
        willUpdateEdgePage(false)
    }

    fun onChangePage(position: Int) {

    }

    fun willUpdateEdgePage(isFirst: Boolean) {}
}
