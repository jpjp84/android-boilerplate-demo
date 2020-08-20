package com.jp.boilerplate.data.datasource

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.util.CalendarMap
import com.jp.boilerplate.util.YearMonths
import java.time.YearMonth
import java.util.*

interface CalendarDataSource : BaseDataSource<Day> {

    fun observeDay(): LiveData<Day>

    fun observeCalendar(): LiveData<CalendarMap>

    suspend fun updateCalendar(yearMonths: YearMonths)

}
