package com.jintin.viewmodelsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val textLiveData = MutableLiveData<String>()

    val textLengthLiveData: LiveData<Int> = Transformations.map(textLiveData) {
        it.length
    }

    fun setText(value: String) {
        textLiveData.value = value
    }
}