package com.jp.boilerplate.data.datasource.remote

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day

class CalendarRemoteDataSource : CalendarDataSource {
    override fun observeDay(): LiveData<Day> {
        TODO("Not yet implemented")
    }

    override suspend fun get(): Day {
        TODO("Not yet implemented")
    }

    override suspend fun set(it: Day): Day {
        TODO("Not yet implemented")
    }
}
