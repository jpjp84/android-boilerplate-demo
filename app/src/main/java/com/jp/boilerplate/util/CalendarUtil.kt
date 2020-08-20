package com.jp.boilerplate.util

import android.icu.util.Calendar
import com.jp.boilerplate.data.entity.Day
import com.jp.boilerplate.data.entity.Days
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*


typealias YearMonths = LinkedList<YearMonth>
typealias CalendarMap = SortedMap<YearMonth, Days>

object CalendarUtil {
    val currentDay: String = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(Date())

    fun createYearMonth(year: Int = YearMonth.now().year, month: Int = YearMonth.now().monthValue): List<Day> =
        Calendar.getInstance()
            .apply {
                set(year, month - 1, 1, 0, 0, 0)
                add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - get(Calendar.DAY_OF_WEEK))
            }
            .let { calendar ->
                List(42) {
                    calendar.add(Calendar.DATE, if (it == 0) 0 else 1)
                    val isInMonth = month - 1 == calendar[Calendar.MONTH] && year == calendar[Calendar.YEAR]
                    val key = "${calendar[Calendar.YEAR]}${
                        String.format(
                            "%02d%02d",
                            calendar[Calendar.MONTH] + 1,
                            calendar[Calendar.DATE]
                        )
                    }"
                    Day(key, disable = isInMonth)
                }
            }
}
