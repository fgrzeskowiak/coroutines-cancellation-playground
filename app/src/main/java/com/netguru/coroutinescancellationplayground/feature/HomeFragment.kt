package com.netguru.coroutinescancellationplayground.feature

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.netguru.coroutinescancellationplayground.R
import com.netguru.coroutinescancellationplayground.databinding.FragmentHomeBinding
import com.netguru.coroutinescancellationplayground.utils.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exceptions.setOnClickListener {
            findNavController().navigate(R.id.exceptionsFragment)
        }
        binding.cancellation.setOnClickListener {
            findNavController().navigate(R.id.cancellationFragment)
        }
        binding.jobs.setOnClickListener {
            findNavController().navigate(R.id.jobsFragment)
        }
    }
}
