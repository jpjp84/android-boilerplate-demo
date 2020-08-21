package com.jp.boilerplate.util.dispatcher

interface CalendarPagerListener {
    enum class EdgePageState {
        FIRST, LAST, ALL
    }

    fun onChangePage(position: Int) {

    }

    fun willUpdateEdgePage(state: EdgePageState) {}
}
