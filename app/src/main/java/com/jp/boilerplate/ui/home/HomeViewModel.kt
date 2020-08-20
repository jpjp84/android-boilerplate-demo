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
import com.jp.boilerplate.util.dispatcher.CalendarPagerListener
import com.jp.boilerplate.util.notifyDataChange
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import java.time.YearMonth
import java.util.*

@ObsoleteCoroutinesApi
class HomeViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val calendarRepository: CalendarRepository
) : BaseViewModel(), CalendarPagerListener {

    private val _forceUpdateUser = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Result<Void>> = _forceUpdateUser.switchMap { userRepository.refreshUser(it) }

    private val _user = userRepository.observable()
    val user: LiveData<User> = _user

    private val _yearMonths = MutableLiveData<YearMonths>(LinkedList())
    val calendar = _yearMonths.switchMap {
        viewModelScope.launch { calendarRepository.updateCalendar(it) }
        calendarRepository.observableCalendar()
    }

    init {
        _forceUpdateUser.value = true
        _yearMonths.value?.apply {
            add(YearMonth.now())
        }
    }

    override fun onFirstPage() {
        super.onFirstPage()
        _yearMonths.value?.let {
            it.addFirst(it.first.minusMonths(1))
            _yearMonths.notifyDataChange()
        }
    }

    override fun onLastPage() {
        super.onLastPage()
        _yearMonths.value?.let {
            it.add(it.last.plusMonths(1))
            _yearMonths.notifyDataChange()
        }
    }
}
