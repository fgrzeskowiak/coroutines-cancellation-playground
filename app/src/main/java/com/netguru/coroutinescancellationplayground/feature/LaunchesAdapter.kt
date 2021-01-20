package com.netguru.coroutinescancellationplayground.feature

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import com.netguru.coroutinescancellationplayground.R
import com.netguru.coroutinescancellationplayground.common.BaseViewHolder
import com.netguru.coroutinescancellationplayground.common.DiffUtilCallback
import com.netguru.coroutinescancellationplayground.databinding.ItemLaunchBinding
import com.netguru.coroutinescancellationplayground.model.RocketLaunch

class LaunchesAdapter : ListAdapter<RocketLaunch, LaunchViewHolder>(
    DiffUtilCallback<RocketLaunch>()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LaunchViewHolder(parent, ItemLaunchBinding::inflate)

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LaunchViewHolder(
    parent: ViewGroup,
    inflater: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> ItemLaunchBinding
) : BaseViewHolder<ItemLaunchBinding, RocketLaunch>(parent, inflater) {
    override fun bind(item: RocketLaunch) {
        binding.name.text = context.getString(R.string.launch_name, item.missionName)
        binding.status.text = launchStatus(item.successful)
        binding.status.setTextColor(statusColor(item.successful))
        binding.year.text =
            context.getString(R.string.launch_year, item.launchDateUTC.year.toString())
        binding.details.isVisible = item.details != null
        binding.details.text = context.getString(R.string.launch_details, item.details)
    }

    private fun launchStatus(successful: Boolean?) =
        context.getString(successful?.let { if (successful) R.string.launch_status_successful else R.string.launch_status_unsuccessful }
            ?: R.string.launch_status_no_data
        )

    private fun statusColor(successful: Boolean?) =
        successful?.let { if (successful) Color.GREEN else Color.RED } ?: Color.GRAY
}
