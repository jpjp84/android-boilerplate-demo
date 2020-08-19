package com.jp.boilerplate.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.jp.boilerplate.data.entity.User
import com.jp.boilerplate.data.meta.Result
import com.jp.boilerplate.data.repository.CalendarRepository
import com.jp.boilerplate.data.repository.UserRepository
import com.jp.boilerplate.ui.base.BaseViewModel
import com.jp.boilerplate.util.YearMonths
import com.jp.boilerplate.util.notifyDataChange
import com.orhanobut.logger.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.YearMonth

class HomeViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val calendarRepository: CalendarRepository
) : BaseViewModel() {

    private val _forceUpdateUser = MutableLiveData<Boolean>(false)
    private val _dataLoading = _forceUpdateUser.switchMap { userRepository.refreshUser(it) }
    val dataLoading: LiveData<Result<Void>> = _dataLoading

    private val _user = userRepository.observable()
    val user: LiveData<User> = _user

    private val _yearMonths = MutableLiveData<YearMonths>(YearMonths(listOf()))
    val calendar = _yearMonths.switchMap {
        viewModelScope.launch { calendarRepository.updateCalendar(it) }
        calendarRepository.observableCalendar(it)
    }

    init {
        _forceUpdateUser.value = true
    }

    fun update() {
        _yearMonths.value?.add(YearMonth.of(2019, 2))
        viewModelScope.launch {
            delay(3000)
            _yearMonths.value?.add(YearMonth.of(2019, 5))
            _yearMonths.notifyDataChange()

            delay(3000)
            _yearMonths.value?.addFirst(YearMonth.of(2017, 5))
            _yearMonths.notifyDataChange()
        }
    }
}
