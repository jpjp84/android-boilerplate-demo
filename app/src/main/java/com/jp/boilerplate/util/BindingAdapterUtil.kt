package com.jp.boilerplate.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jp.boilerplate.ui.home.CalendarPageAdapter
import com.orhanobut.logger.Logger


@BindingAdapter("bindItems")
fun bindItems(recyclerView: RecyclerView, days: CalendarMap?) {
    days?.let {
        Logger.d("Binding adapter : calendar : $days")
        (recyclerView.adapter as CalendarPageAdapter?)?.submitList(it)
    }
}
