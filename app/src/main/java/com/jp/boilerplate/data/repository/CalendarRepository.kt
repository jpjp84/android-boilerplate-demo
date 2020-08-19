package com.jp.boilerplate.data.repository

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.meta.Result
import com.jp.boilerplate.util.CalendarMap
import java.time.YearMonth
import java.util.*

interface CalendarRepository {

    fun observable(): LiveData<Day>

    fun observableCalendar(yearMonths: LinkedList<YearMonth>): LiveData<CalendarMap>

    suspend fun updateCalendar(yearMonths: LinkedList<YearMonth>)

    fun refreshDay(forceUpdate: Boolean): LiveData<Result<Void>>

    suspend fun setDay(user: Day)
}
