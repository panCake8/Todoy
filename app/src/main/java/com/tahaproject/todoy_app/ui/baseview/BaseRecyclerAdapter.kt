package com.tahaproject.todoy_app.ui.baseview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerAdapter<T, VB : ViewBinding>(private var list: List<T>) :
    RecyclerView.Adapter<BaseRecyclerAdapter<T, VB>.BaseRecycleViewHolder>() {
    abstract val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecycleViewHolder {
        val binding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return BaseRecycleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseRecycleViewHolder, position: Int) {
        bindViews(holder.binding)
    }

    abstract fun bindViews(binding: VB)

    override fun getItemCount() = list.size ?: 0


    inner class BaseRecycleViewHolder(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)

}