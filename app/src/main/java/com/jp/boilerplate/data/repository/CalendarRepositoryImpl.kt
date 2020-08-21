package com.jp.boilerplate.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.meta.Result
import com.jp.boilerplate.util.CalendarMap
import com.jp.boilerplate.util.YearMonths
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlin.coroutines.CoroutineContext


class CalendarRepositoryImpl constructor(
    private val calendarLocalDataSource: CalendarDataSource,
    private val calendarRemoteDataSource: CalendarDataSource
) : CalendarRepository, CoroutineScope {

    sealed class CalendarActor {
        class Update(val yearMonths: YearMonths, val response: CompletableDeferred<Int>) : CalendarActor()
    }

    override fun observable(): LiveData<Day> {
        TODO("Not yet implemented")
    }

    override fun observableCalendar(): LiveData<CalendarMap> =
        calendarLocalDataSource.observeCalendar().distinctUntilChanged()

    @ObsoleteCoroutinesApi
    override suspend fun updateCalendar(yearMonths: YearMonths) {
        val completable = CompletableDeferred<Int>()
        calendarLocalDataSource.updateCalendar(yearMonths, completable)
    }

    override fun refreshDay(forceUpdate: Boolean): LiveData<Result<Void>> {
        TODO("Not yet implemented")
    }

    override suspend fun setDay(user: Day) {
        TODO("Not yet implemented")
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}
