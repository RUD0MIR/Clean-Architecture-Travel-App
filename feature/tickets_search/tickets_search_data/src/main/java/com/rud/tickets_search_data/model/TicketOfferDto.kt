package com.rud.tickets_search_data.model

import com.rud.tickets_search_domain.model.TicketOffer

data class TicketOfferDto(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)

fun TicketOfferDto.toTicketOffer(): TicketOffer{
    return TicketOffer(
        id = this.id,
        title = this.title,
        town = this.town,
        price = this.price.value
    )
}