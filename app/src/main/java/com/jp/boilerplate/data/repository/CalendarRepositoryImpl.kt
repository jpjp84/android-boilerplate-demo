package com.jp.boilerplate.data.repository

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.meta.Result

class CalendarRepositoryImpl constructor(
    private val userLocalDataSource: CalendarDataSource,
    private val userRemoteDataSource: CalendarDataSource
) : CalendarRepository {
    override fun observable(): LiveData<Day> {
        TODO("Not yet implemented")
    }

    override fun refreshDay(forceUpdate: Boolean): LiveData<Result<Void>> {
        TODO("Not yet implemented")
    }

    override suspend fun setDay(user: Day) {
        TODO("Not yet implemented")
    }
}
