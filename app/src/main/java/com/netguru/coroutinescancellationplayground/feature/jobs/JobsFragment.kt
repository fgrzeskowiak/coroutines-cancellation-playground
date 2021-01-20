package com.netguru.coroutinescancellationplayground.feature.jobs

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.netguru.coroutinescancellationplayground.R
import com.netguru.coroutinescancellationplayground.databinding.FragmentLaunchesBinding
import com.netguru.coroutinescancellationplayground.feature.LaunchesAdapter
import com.netguru.coroutinescancellationplayground.utils.viewBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class JobsFragment : Fragment(R.layout.fragment_launches) {
    private val binding by viewBinding(FragmentLaunchesBinding::bind)
    private val viewModel by viewModel<JobsViewModel>()
    private val launchesAdapter by inject<LaunchesAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.launchesLiveData.observe(viewLifecycleOwner) { state ->
            binding.progress.isVisible = state.isLoading
            binding.errorMessage.isVisible = state.isError
            binding.launchesRecyclerView.isVisible = state.isSuccess

            state
                .onSuccess(launchesAdapter::submitList)
                .onError { binding.errorMessage.text = it.message ?: "Error" }
        }
    }

    private fun initRecyclerView() {
        binding.launchesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = launchesAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}
