package com.rud.tickets_search_data.model

import com.rud.tickets_search_domain.model.TicketOffer

internal data class TicketOfferDto(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)

