package com.rud.tickets_search_presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rud.tickets_search_presentation.databinding.TicketRecommendationItemBinding

class TicketsRecommendationsAdapter():
RecyclerView.Adapter<TicketsRecommendationsAdapter.TicketsRecommendationsViewHolder>() {

    inner class TicketsRecommendationsViewHolder(val binding: TicketRecommendationItemBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TicketsRecommendationsAdapter.TicketsRecommendationsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: TicketsRecommendationsAdapter.TicketsRecommendationsViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}