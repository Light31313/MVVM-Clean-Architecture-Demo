package com.giangnh44.base_lib.extension.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BindingViewHolder<VB : ViewBinding>(private val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    val context: Context = binding.root.context
}