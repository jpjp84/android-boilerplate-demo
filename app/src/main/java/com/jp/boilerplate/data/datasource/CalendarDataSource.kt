package com.jp.boilerplate.data.datasource

import androidx.lifecycle.LiveData
import com.jp.boilerplate.data.entity.Day

interface CalendarDataSource : BaseDataSource<Day> {

    fun observeDay(): LiveData<Day>

}
