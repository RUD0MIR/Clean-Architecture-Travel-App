package com.rud.tickets_search_domain.model

data class TicketRecommendation(
    val id: Int,
    val price: Int,
    val timeRange: List<String>,
    val title: String
)