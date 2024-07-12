package com.rud.airlinetickets.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rud.airlinetickets.R
import com.rud.airlinetickets.adapter.RecommendedArrivalsAdapter
import com.rud.airlinetickets.databinding.FragmentSearchBinding
import com.rud.airlinetickets.onRightCompoundDrawableClick
import com.rud.domain.model.RecommendedArrival


class SearchFragment(private val departure: String) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setDialogFillMaxWindowHeight()
        setUpRecyclerView()
        binding.etDeparture.text = departure
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
                replaceFragment(
                    DetailedSearchFragment(
                        arrival = "Куда угодно",
                        departure = binding.etDeparture.text.toString()
                    )
                )
            }
            ivWeekends.setOnClickListener {
                replaceFragment(BackButtonFragment())
            }
            ivHardTrail.setOnClickListener {
                replaceFragment(BackButtonFragment())
            }
            ivHotTickets.setOnClickListener {
                replaceFragment(BackButtonFragment())
            }
        }
    }

    private fun setUpRecyclerView() {
        val recommendedArrivalsAdapter = RecommendedArrivalsAdapter() { arrivalTitle ->
            replaceFragment(
                DetailedSearchFragment(
                    arrival = arrivalTitle,
                    departure = binding.etDeparture.text.toString()
                )
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

    private fun replaceFragment(fragment: Fragment) {
        val ft: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_fragment, fragment, fragment.tag)
        ft.commit()
        ft.addToBackStack(null)
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