package com.rud.data.remote


import com.rud.domain.model.Offer
import com.rud.domain.model.Ticket
import com.rud.domain.model.TicketOffer
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface AirlineApi {
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getOffers(): Response<ArrayList<Offer>>

    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffers(): Response<ArrayList<TicketOffer>>

    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): Response<ArrayList<Ticket>>
}