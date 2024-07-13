package com.rud.tickets_search_domain.repository

import com.rud.common.Resource
import com.rud.tickets_search_domain.model.TicketOffer
import kotlinx.coroutines.flow.Flow

interface TicketsOffersRepository {
    suspend fun getTicketsOffers() : Flow<Resource<List<TicketOffer>>>
}