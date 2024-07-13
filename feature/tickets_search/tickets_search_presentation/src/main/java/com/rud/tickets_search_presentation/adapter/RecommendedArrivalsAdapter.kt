package com.rud.tickets_search_presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rud.tickets_search_domain.model.RecommendedArrival
import com.rud.tickets_search_presentation.R
import com.rud.tickets_search_presentation.databinding.RecommendedArrivalItemBinding

class RecommendedArrivalsAdapter(
    val onItemClick: (title: String) -> Unit
) : RecyclerView.Adapter<RecommendedArrivalsAdapter.RecommendedArrivalsViewHolder>() {

    inner class RecommendedArrivalsViewHolder(val binding: RecommendedArrivalItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<RecommendedArrival>() {
        override fun areItemsTheSame(
            oldItem: RecommendedArrival,
            newItem: RecommendedArrival
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecommendedArrival,
            newItem: RecommendedArrival
        ): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var recommendedArrivals: List<RecommendedArrival>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedArrivalsViewHolder {
        return RecommendedArrivalsViewHolder(
            RecommendedArrivalItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: RecommendedArrivalsViewHolder, position: Int) {
        val currentArrival = recommendedArrivals[position]
        viewHolder.binding.apply {
            tvRecommendedArrivalTitle.text = currentArrival.title
            tvRecommendedArrivalDescription.text = "Популярные направления"

            clArrivalItem.setOnClickListener {
                onItemClick(currentArrival.title)
            }

            when (position) {
                0 -> ivArrival.setImageResource(R.drawable.img_arrival1)
                1 -> ivArrival.setImageResource(R.drawable.img_arrival2)
                2 -> ivArrival.setImageResource(R.drawable.img_arrival3)
            }

        }
    }

    override fun getItemCount() = recommendedArrivals.size

}
