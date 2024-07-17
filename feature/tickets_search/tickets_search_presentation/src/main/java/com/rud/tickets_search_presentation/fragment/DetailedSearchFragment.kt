package com.rud.tickets_search_presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rud.tickets_search_presentation.adapter.TicketsOffersAdapter
import com.rud.tickets_search_presentation.adapter.TicketsRecommendationsAdapter
import com.rud.tickets_search_presentation.databinding.FragmentDetailedSearchBinding
import com.rud.tickets_search_presentation.onRightCompoundDrawableClick
import com.rud.tickets_search_presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedSearchFragment() : Fragment() {
    private lateinit var binding: FragmentDetailedSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val args: DetailedSearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedSearchBinding.inflate(inflater, container, false)

        binding.tvArrival.text = args.arrival
        binding.tvDeparture.text = args.departure
        onIvArrowBackClick()
        onSwapIconClick()
        onClearArrivalIconClick()
        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {
        val recommendationsAdapter = TicketsRecommendationsAdapter()
        binding.rvTicketsRecommendations.apply {
            adapter = recommendationsAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false
            )
        }

        viewModel.ticketsRecommendations.observe(viewLifecycleOwner) {
            recommendationsAdapter.ticketsRecommendations = it
        }
    }

    private fun onIvArrowBackClick() {
        binding.ivArrowBack.setOnClickListener {
            val action =
                DetailedSearchFragmentDirections
                    .actionDetailedSearchFragmentToSearchFragment(binding.tvDeparture.text.toString())
            findNavController().navigate(action)
        }
    }

    private fun onSwapIconClick() {
        binding.tvDeparture.onRightCompoundDrawableClick {
            val departureText = binding.tvDeparture.text.toString()
            val arrivalText = binding.tvArrival.text.toString()

            binding.tvDeparture.text = arrivalText
            binding.tvArrival.text = departureText
        }
    }

    private fun onClearArrivalIconClick() {
        binding.tvArrival.onRightCompoundDrawableClick {
            findNavController().popBackStack()
        }
    }
}