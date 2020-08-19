package com.jp.boilerplate.data.datasource.remote

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.util.CalendarMap
import java.time.YearMonth
import java.util.*

class CalendarRemoteDataSource : CalendarDataSource {
    override fun observeDay(): LiveData<Day> {
        TODO("Not yet implemented")
    }

    override fun observeCalendar(): LiveData<CalendarMap> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCalendar(yearMonths: LinkedList<YearMonth>) {
        TODO("Not yet implemented")
    }

    override suspend fun get(): Day {
        TODO("Not yet implemented")
    }

    override suspend fun set(it: Day): Day {
        TODO("Not yet implemented")
    }
}
