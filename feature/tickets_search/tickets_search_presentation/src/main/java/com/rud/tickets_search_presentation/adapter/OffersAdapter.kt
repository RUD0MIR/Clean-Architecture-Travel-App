package com.rud.tickets_search_presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rud.tickets_search_domain.model.TicketOffer
import com.rud.tickets_search_presentation.R
import com.rud.tickets_search_presentation.databinding.OfferListItemBinding

class OffersAdapter() : RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {

    inner class OffersViewHolder(val binding: OfferListItemBinding) :
    RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TicketOffer>() {
        override fun areItemsTheSame(oldItem: TicketOffer, newItem: TicketOffer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TicketOffer, newItem: TicketOffer): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var ticketsOffers: List<TicketOffer>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        return OffersViewHolder(
            OfferListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: OffersViewHolder, position: Int) {
        val currentOffer = ticketsOffers[position]
        viewHolder.binding.apply {
            tvTitle.text = currentOffer.title
            tvPrice.text = currentOffer.price.toString()
            tvTown.text = currentOffer.town

            when(currentOffer.id) {
                1 -> ivOfferImage.setImageResource(R.drawable.placeholder1)
                2 -> ivOfferImage.setImageResource(R.drawable.placeholder2)
            }

        }
    }

    override fun getItemCount() = ticketsOffers.size
}
