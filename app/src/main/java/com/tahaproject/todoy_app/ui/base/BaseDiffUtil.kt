package com.tahaproject.todoy_app.ui.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val areItemsSame: (oldItem: T, newItem: T) -> Boolean,
    private val areContentSame: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentSame(oldList[oldItemPosition], newList[newItemPosition])
    }
}