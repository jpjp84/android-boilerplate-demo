package com.jp.boilerplate.data.datasource.local

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.meta.db.UserDao

class CalendarLocalDataSource(
    private val userDao: UserDao
) : CalendarDataSource {
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
