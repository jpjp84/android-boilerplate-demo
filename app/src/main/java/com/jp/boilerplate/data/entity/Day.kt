package com.jp.boilerplate.data.entity

import androidx.room.Entity
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*

@Entity(tableName = "calendar")
data class Day constructor(
    val date: String,
    val recipes: List<Recipe> = mutableListOf(),
    var disable: Boolean = false
) {

    fun getDateToClass(): Int {
        return Calendar.getInstance().apply {
            time = SimpleDateFormat("yyyyMMdd", Locale.KOREA).parse(date)
        }.get(Calendar.DATE)
    }

    fun getYearMonth(): YearMonth {
        return YearMonth.of(date.substring(0..3).toInt(), date.substring(4).toInt())
    }
}

typealias Days = List<Day>
