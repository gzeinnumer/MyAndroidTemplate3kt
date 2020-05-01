package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostVM : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is post Fragment"
    }
    val text: LiveData<String> = _text
}