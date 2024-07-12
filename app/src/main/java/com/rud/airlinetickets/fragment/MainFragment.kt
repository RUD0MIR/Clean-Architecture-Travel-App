package com.rud.airlinetickets.fragment

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.rud.airlinetickets.adapter.OffersAdapter
import com.rud.airlinetickets.databinding.FragmentMainBinding
import com.rud.airlinetickets.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
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
        binding.tvArrival.setOnClickListener {
            viewModel.saveDeparture(
                binding.etDepartureDate.text.toString()
            )

            val dialog = SearchFragment(binding.etDepartureDate.text.toString())
            dialog.show(childFragmentManager, dialog.tag)
        }
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

        viewModel.offers.observe(viewLifecycleOwner) {
            offersAdapter.offers = it
        }
    }

    private fun observeSavedDeparture() {
        if (binding.etDepartureDate.text.toString().isBlank()) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.savedDeparture.collect { departure ->
                        binding.etDepartureDate.setText(departure, TextView.BufferType.EDITABLE)
                    }
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

//    private val testOffers = listOf(
//        Offer(
//            title = "interdum", town = "discere", price = 100, id = 1
//        ),
//        Offer(
//            title = "mauris", town = "elaboraret", price = 100, id = 2
//        ),
//        Offer(
//            title = "mauris", town = "elaboraret", price = 100, id = 3
//        ),
//        Offer(
//            title = "mauris", town = "elaboraret", price = 100, id = 4
//        ),
//        Offer(
//            title = "mauris", town = "elaboraret", price = 100, id = 5
//        )
//    )

