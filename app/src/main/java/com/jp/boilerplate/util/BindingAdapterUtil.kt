package com.jp.boilerplate.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jp.boilerplate.data.entity.Days
import com.jp.boilerplate.ui.home.CalendarAdapter
import com.jp.boilerplate.ui.home.CalendarPageAdapter

@BindingAdapter("bindItems")
fun bindItems(recyclerView: RecyclerView, calendarMap: CalendarMap?) {
    calendarMap?.let { (recyclerView.adapter as? CalendarPageAdapter)?.submitList(it) }
}

@BindingAdapter("bindItems")
fun bindItems(recyclerView: RecyclerView, days: Days?) {
    days?.let { (recyclerView.adapter as? CalendarAdapter)?.submitList(it) }
}
