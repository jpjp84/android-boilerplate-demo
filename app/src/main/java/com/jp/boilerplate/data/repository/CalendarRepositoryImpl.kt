package com.jp.boilerplate.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.meta.Result
import com.jp.boilerplate.util.CalendarMap
import java.time.YearMonth
import java.util.*

class CalendarRepositoryImpl constructor(
    private val calendarLocalDataSource: CalendarDataSource,
    private val calendarRemoteDataSource: CalendarDataSource
) : CalendarRepository {
    override fun observable(): LiveData<Day> {
        TODO("Not yet implemented")
    }

    override fun observableCalendar(): LiveData<CalendarMap> =
        calendarLocalDataSource.observeCalendar().distinctUntilChanged()

    override suspend fun updateCalendar(yearMonths: LinkedList<YearMonth>) {
        calendarLocalDataSource.updateCalendar(yearMonths)
    }

    override fun refreshDay(forceUpdate: Boolean): LiveData<Result<Void>> {
        TODO("Not yet implemented")
    }

    override suspend fun setDay(user: Day) {
        TODO("Not yet implemented")
    }
}
