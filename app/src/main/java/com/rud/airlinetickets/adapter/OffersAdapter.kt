package com.rud.airlinetickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rud.airlinetickets.R
import com.rud.airlinetickets.databinding.OfferListItemBinding
import com.rud.domain.model.Offer

class OffersAdapter() : RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {

    inner class OffersViewHolder(val binding: OfferListItemBinding) :
    RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var offers: List<Offer>
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
        val currentOffer = offers[position]
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

    override fun getItemCount() = offers.size

}
