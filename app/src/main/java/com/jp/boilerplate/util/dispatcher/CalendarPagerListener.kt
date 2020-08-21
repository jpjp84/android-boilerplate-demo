package com.jp.boilerplate.util.dispatcher

interface CalendarPagerListener {

    fun onFirstPage() {
    }

    fun onLastPage() {
    }

    fun onChangePage(position: Int) {

    }
}
