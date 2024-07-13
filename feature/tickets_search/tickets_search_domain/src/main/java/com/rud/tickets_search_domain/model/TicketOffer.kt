package com.rud.tickets_search_domain.model

data class TicketOffer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Int,
)