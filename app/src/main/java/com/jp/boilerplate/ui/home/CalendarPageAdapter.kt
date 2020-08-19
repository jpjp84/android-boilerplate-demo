package com.jp.boilerplate.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jp.boilerplate.databinding.RowCalendarPageBinding
import com.jp.boilerplate.util.CalendarMap
import java.time.YearMonth

class CalendarPageAdapter(private val viewModel: HomeViewModel) :
    ListAdapter<YearMonth, CalendarPageAdapter.CalendarPageViewHolder>(MonthDiffCallback()) {

    fun submitList(yearMonthMap: CalendarMap) {
        super.submitList(yearMonthMap.keys.toList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarPageViewHolder {
        return CalendarPageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CalendarPageViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }

    class CalendarPageViewHolder constructor(private val binding: RowCalendarPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var adapter: CalendarAdapter

        fun bind(viewModel: HomeViewModel, yearMonth: YearMonth) {
            binding.viewModel = viewModel
            binding.currentMonth = yearMonth
            adapter = CalendarAdapter(viewModel)
            binding.calendarPageView.adapter = adapter
        }

        companion object {
            fun from(parent: ViewGroup): CalendarPageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowCalendarPageBinding.inflate(layoutInflater, parent, false)

                return CalendarPageViewHolder(binding)
            }
        }
    }
}

class MonthDiffCallback : DiffUtil.ItemCallback<YearMonth>() {
    override fun areItemsTheSame(oldItem: YearMonth, newItem: YearMonth): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: YearMonth, newItem: YearMonth): Boolean {
        return oldItem == newItem
    }
}
