package com.rud.airlinetickets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rud.airlinetickets.databinding.FragmentDetailedSearchBinding
import com.rud.airlinetickets.onRightCompoundDrawableClick

class DetailedSearchFragment(
    val arrival: String,
    val departure: String
) : Fragment() {
    private lateinit var binding: FragmentDetailedSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedSearchBinding.inflate(inflater, container, false)

        binding.tvArrival.text = arrival
        binding.tvDeparture.text = departure
        onIvArrowBackClick()
        onSwapIconClick()
        onClearArrivalIconClick()

        return binding.root
    }

    private fun onIvArrowBackClick() {
        binding.ivArrowBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
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
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}