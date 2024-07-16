package com.rud.tickets_search_domain.repository

import com.rud.common.Resource
import com.rud.tickets_search_domain.model.TicketRecommendation
import kotlinx.coroutines.flow.Flow

interface TicketsRecommendationsRepository {
   suspend fun getTicketsRecommendations(): Flow<Resource<List<TicketRecommendation>>>
}