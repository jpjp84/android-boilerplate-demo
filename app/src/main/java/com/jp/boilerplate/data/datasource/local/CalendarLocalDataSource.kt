package com.jp.boilerplate.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.meta.db.UserDao
import com.jp.boilerplate.util.CalendarMap
import com.jp.boilerplate.util.CalendarUtil
import com.jp.boilerplate.util.YearMonths
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalendarLocalDataSource(
    private val userDao: UserDao
) : CalendarDataSource {

    private var observableCalendar = MutableLiveData<CalendarMap>(sortedMapOf())

    override fun observeDay(): LiveData<Day> {
        TODO("Not yet implemented")
    }

    override fun observeCalendar(): LiveData<CalendarMap> = observableCalendar

    override suspend fun updateCalendar(yearMonths: YearMonths, response: CompletableDeferred<Int>) {
        observableCalendar.value = getUpdatedCalendarMap(yearMonths)
    }

    private suspend fun getUpdatedCalendarMap(yearMonths: YearMonths): CalendarMap = withContext(Dispatchers.IO) {
        (observableCalendar.value?.toSortedMap() ?: sortedMapOf()).apply {
            yearMonths.map { yearMonth ->
                computeIfAbsent(yearMonth) { CalendarUtil.createYearMonth(it.year, it.monthValue) }
            }
        }
    }

    override suspend fun get(): Day {
        TODO("Not yet implemented")
    }

    override suspend fun set(it: Day): Day {
        TODO("Not yet implemented")
    }
}
