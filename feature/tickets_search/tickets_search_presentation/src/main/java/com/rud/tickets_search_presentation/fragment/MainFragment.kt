package com.rud.tickets_search_presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val TAG = "MainFragment"

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setLastDeparture()
        saveDepartureOnTextChange()

        setUpRecyclerView()
        onTvArrivalClick()

        return binding.root
    }

    private fun onTvArrivalClick() {
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

    private fun saveDepartureOnTextChange() {
        binding.etDepartureDate.doOnTextChanged { text, _, _, _ ->
            viewModel.saveDeparture(text.toString())
        }
    }

    private fun setLastDeparture() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.departure.collect { departure ->
                    if(binding.etDepartureDate.text.toString() == "") {
                        binding.etDepartureDate.setText(departure)
                    }
                }
            }
        }
    }
}

