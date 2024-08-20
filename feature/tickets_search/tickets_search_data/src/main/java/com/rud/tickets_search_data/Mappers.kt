package com.rud.tickets_search_data

import com.rud.tickets_search_data.model.TicketOfferDto
import com.rud.tickets_search_data.model.TicketRecommendationDto
import com.rud.tickets_search_domain.model.TicketOffer
import com.rud.tickets_search_domain.model.TicketRecommendation

internal fun TicketOfferDto.toDomain(): TicketOffer {
    return TicketOffer(
        id = this.id,
        title = this.title,
        town = this.town,
        price = this.price.value
    )
}

internal fun TicketRecommendationDto.toDomain() =
    TicketRecommendation(
        id = id,
        price = price.value,
        timeRange = timeRange,
        title = title
    )