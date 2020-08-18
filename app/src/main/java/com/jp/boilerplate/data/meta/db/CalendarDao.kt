package com.jp.boilerplate.data.meta.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.entity.User

@Dao
interface CalendarDao : BaseDao<Day> {

    @Query("SELECT * FROM calendar")
    fun observable(): LiveData<Day>

    @Query("SELECT * FROM calendar")
    fun select(): Day
}
