package com.jp.boilerplate.data.repository

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.entity.User
import com.jp.boilerplate.data.meta.Result

interface CalendarRepository {

    fun observable(): LiveData<Day>

    fun refreshDay(forceUpdate: Boolean): LiveData<Result<Void>>

    suspend fun setDay(user: Day)
}
