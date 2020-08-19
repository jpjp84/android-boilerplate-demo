package com.jp.boilerplate.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.meta.db.UserDao
import com.jp.boilerplate.util.CalendarMap
import com.jp.boilerplate.util.CalendarUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.YearMonth
import java.util.*

class CalendarLocalDataSource(
    private val userDao: UserDao
) : CalendarDataSource {

    private var observableCalendar = MutableLiveData<CalendarMap>(mutableMapOf())

    override fun observeDay(): LiveData<Day> {
        TODO("Not yet implemented")
    }

    override fun observeCalendar(): LiveData<CalendarMap> = observableCalendar

    override suspend fun updateCalendar(yearMonths: LinkedList<YearMonth>) {
        observableCalendar.value = getUpdatedCalendarMap(yearMonths)
    }

    private suspend fun getUpdatedCalendarMap(yearMonths: LinkedList<YearMonth>): CalendarMap =
        withContext(Dispatchers.IO) {
            (observableCalendar.value?.toMutableMap() ?: mutableMapOf()).apply {
                yearMonths.map { yearMonth -> computeIfAbsent(yearMonth) { CalendarUtil.createYearMonth() } }
            }
        }

    override suspend fun get(): Day {
        TODO("Not yet implemented")
    }

    override suspend fun set(it: Day): Day {
        TODO("Not yet implemented")
    }
}
