package com.jp.boilerplate.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.databinding.RowCalendarBinding
import com.orhanobut.logger.Logger

class CalendarAdapter(private val viewModel: HomeViewModel) :
    ListAdapter<Day, CalendarAdapter.CalendarViewHolder>(DayDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }

    class CalendarViewHolder constructor(private val binding: RowCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: HomeViewModel, items: Day) {
            binding.viewModel = viewModel
            Logger.d("item : $items")
        }

        companion object {
            fun from(parent: ViewGroup): CalendarViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowCalendarBinding.inflate(layoutInflater, parent, false)

                return CalendarViewHolder(binding)
            }
        }
    }
}

class DayDiffCallback : DiffUtil.ItemCallback<Day>() {
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
        return true
    }
}
