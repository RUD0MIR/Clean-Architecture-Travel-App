package com.rud.tickets_search_data.remote


import com.rud.tickets_search_data.model.Ticket
import com.rud.tickets_search_data.model.TicketOfferDto
import com.rud.tickets_search_data.model.TicketRecommendationDto
import retrofit2.http.GET

internal interface TicketsApi {
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getTicketsOffers(): List<TicketOfferDto>

    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsRecommendations(): List<TicketRecommendationDto>

    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): List<Ticket>
}