package com.rud.tickets_search_data.model

import com.google.gson.annotations.SerializedName
import com.rud.tickets_search_domain.model.TicketRecommendation

internal data class TicketRecommendationDto(
    val id: Int,
    val price: Price,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val title: String
)

