package com.gzeinnumer.myandroidtemplate3kt.util

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtilsCallBack<T>(
    private val oldList: List<T>?,
    private val newList: List<T>?
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList?.size!!
    }

    override fun getNewListSize(): Int {
        return newList?.size!!
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList?.get(oldItemPosition) == this.newList?.get(newItemPosition)
    }

}
