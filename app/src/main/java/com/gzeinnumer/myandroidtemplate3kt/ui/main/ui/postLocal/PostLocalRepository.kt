package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.postLocal

import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost

interface PostLocalRepository {
    fun getList(): List<ResponsePost>
}