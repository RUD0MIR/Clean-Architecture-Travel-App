package com.rud.tickets_search_presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rud.tickets_search_domain.model.TicketRecommendation
import com.rud.tickets_search_presentation.R
import com.rud.tickets_search_presentation.addSpaces
import com.rud.tickets_search_presentation.databinding.TicketRecommendationItemBinding

class TicketsRecommendationsAdapter():
RecyclerView.Adapter<TicketsRecommendationsAdapter.TicketsRecommendationsViewHolder>() {

    inner class TicketsRecommendationsViewHolder(val binding: TicketRecommendationItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TicketRecommendation>() {
        override fun areItemsTheSame(oldItem: TicketRecommendation, newItem: TicketRecommendation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TicketRecommendation, newItem: TicketRecommendation): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var ticketsRecommendations: List<TicketRecommendation>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsRecommendationsViewHolder {
        return TicketsRecommendationsViewHolder(
            TicketRecommendationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: TicketsRecommendationsViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        val currentRecommendation = ticketsRecommendations[position]
        viewHolder.binding.apply {
            tvTicketRecommendationTitle.text = currentRecommendation.title
            tvTicketRecommendationPrice.text = getFormattedPrice(
                context, currentRecommendation.price
            )
            tvTicketRecommendationTimeRange.text = currentRecommendation.timeRange.joinToString(" ")

            when (position) {
                0 -> ivCircle.setColorFilter(context.getColor(com.rud.design_system.R.color.red))
                1 -> ivCircle.setColorFilter(context.getColor(com.rud.design_system.R.color.blue))
                2 -> ivCircle.setColorFilter(context.getColor(com.rud.design_system.R.color.white))
            }
        }
    }

    override fun getItemCount() = ticketsRecommendations.size

    private fun getFormattedPrice(context: Context, price: Int): String {
        return context.getString(R.string.price_with_sign, price.addSpaces())
    }
}