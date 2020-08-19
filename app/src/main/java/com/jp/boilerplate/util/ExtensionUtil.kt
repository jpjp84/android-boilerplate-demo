package com.jp.boilerplate.util

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notifyDataChange() {
    this.value = this.value
}
