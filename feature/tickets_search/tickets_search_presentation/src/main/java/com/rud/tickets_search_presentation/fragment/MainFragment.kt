package com.rud.tickets_search_presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rud.tickets_search_presentation.adapter.OffersAdapter
import com.rud.tickets_search_presentation.databinding.FragmentMainBinding
import com.rud.tickets_search_presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        observeSavedDeparture()
        onTvArrivalClick()

        return binding.root
    }

    private fun onTvArrivalClick() {
        viewModel.saveDeparture(
            binding.etDepartureDate.text.toString()
        )

        binding.tvArrival.setOnClickListener {
            navigateToSearchFragment(binding.etDepartureDate.text.toString())
        }
    }

    private fun navigateToSearchFragment(departure: String) {
        val action =
            MainFragmentDirections
                .actionMainFragmentToSearchFragment(departure)
        findNavController().navigate(action)
    }

    private fun setUpRecyclerView() {
        val offersAdapter = OffersAdapter()
        binding.rvOffers.apply {
            adapter = offersAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        viewModel.ticketsOffers.observe(viewLifecycleOwner) {
            offersAdapter.ticketsOffers = it
        }
    }

    private fun observeSavedDeparture() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.savedDeparture.collect { departure ->
                    binding.etDepartureDate.setText(departure, TextView.BufferType.EDITABLE)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveDeparture(
            binding.etDepartureDate.text.toString()
        )
    }
}

