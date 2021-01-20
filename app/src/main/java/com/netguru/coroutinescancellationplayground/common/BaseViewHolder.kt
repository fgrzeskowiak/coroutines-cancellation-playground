package com.netguru.coroutinescancellationplayground.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T : ViewBinding, in M : UniqueId> private constructor(
    val binding: T
) : RecyclerView.ViewHolder(binding.root) {

    protected val context: Context = itemView.context

    constructor(
        parent: ViewGroup,
        creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
    ) : this(creator(LayoutInflater.from(parent.context), parent, false))

    abstract fun bind(item: M)
}
