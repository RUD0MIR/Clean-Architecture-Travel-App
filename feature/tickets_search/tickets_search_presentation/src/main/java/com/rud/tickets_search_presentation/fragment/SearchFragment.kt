package com.rud.tickets_search_presentation.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rud.tickets_search_presentation.adapter.RecommendedArrivalsAdapter
import com.rud.tickets_search_domain.model.RecommendedArrival
import com.rud.tickets_search_presentation.databinding.FragmentSearchBinding
import com.rud.tickets_search_presentation.onRightCompoundDrawableClick


class SearchFragment() : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchBinding
    private val args: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setDialogFillMaxWindowHeight()
        setUpRecyclerView()
        binding.etDeparture.text = args.departure

        setUpTipsSection()
        onClearArrivalIconClick()

        return binding.root
    }

    private fun onClearArrivalIconClick() {
        binding.etArrival.apply { onRightCompoundDrawableClick { setText("") } }
    }

    private fun setUpTipsSection() {
        binding.apply {
            ivAnywhere.setOnClickListener {
                navigateToDetailedSearchFragment(
                    binding.etDeparture.text.toString(),
                    "Куда угодно",
                )
            }
            ivWeekends.setOnClickListener {
                navigateToBackButtonFragment()
            }
            ivHardTrail.setOnClickListener {
                navigateToBackButtonFragment()
            }
            ivHotTickets.setOnClickListener {
                navigateToBackButtonFragment()
            }
        }
    }

    private fun navigateToBackButtonFragment() {
        val action =
            SearchFragmentDirections
                .actionSearchFragmentToBackButtonFragment()
        findNavController().navigate(action)
    }

    private fun navigateToDetailedSearchFragment(departure: String, arrival: String) {
        val action =
            SearchFragmentDirections
                .actionSearchFragmentToDetailedSearchFragment(
                    departure,
                    arrival
                )
        findNavController().navigate(action)
    }


    private fun setUpRecyclerView() {
        val recommendedArrivalsAdapter = RecommendedArrivalsAdapter() { arrivalTitle ->
            navigateToDetailedSearchFragment(
                binding.etDeparture.text.toString(),
                arrivalTitle
            )
        }
        binding.rvArrivals.apply {
            adapter = recommendedArrivalsAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false
            )
        }

        recommendedArrivalsAdapter.recommendedArrivals = TEMP_ARRIVALS
    }

    private fun setDialogFillMaxWindowHeight() {
        val metrics = DisplayMetrics()
        requireActivity().windowManager?.defaultDisplay?.getMetrics(metrics)
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        (dialog as BottomSheetDialog).behavior.peekHeight = metrics.heightPixels

        binding.clBottomSheet.layoutParams.height = metrics.heightPixels
        binding.clBottomSheet.requestLayout()
    }

    val TEMP_ARRIVALS = listOf(
        RecommendedArrival(
            id = 0,
            title = "Стамбул"
        ),
        RecommendedArrival(
            id = 1,
            title = "Сочи"
        ),
        RecommendedArrival(
            id = 2,
            title = "Пхукет"
        ),
    )
}