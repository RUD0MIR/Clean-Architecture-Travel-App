package com.rud.tickets_search_data.model

import com.google.gson.annotations.SerializedName
import com.rud.tickets_search_domain.model.TicketRecommendation

data class TicketRecommendationDto(
    val id: Int,
    val price: Price,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val title: String
)

fun TicketRecommendationDto.toTicketRecommendation() =
    TicketRecommendation(
        id = id,
        price = price.value,
        timeRange = timeRange,
        title = title
    )