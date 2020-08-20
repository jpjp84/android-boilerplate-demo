package com.jp.boilerplate.data.datasource.remote

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.util.CalendarMap
import com.jp.boilerplate.util.YearMonths
import kotlinx.coroutines.CompletableDeferred

class CalendarRemoteDataSource : CalendarDataSource {
    override fun observeDay(): LiveData<Day> {
        TODO("Not yet implemented")
    }

    override fun observeCalendar(): LiveData<CalendarMap> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCalendar(yearMonths: YearMonths, response: CompletableDeferred<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun get(): Day {
        TODO("Not yet implemented")
    }

    override suspend fun set(it: Day): Day {
        TODO("Not yet implemented")
    }
}
