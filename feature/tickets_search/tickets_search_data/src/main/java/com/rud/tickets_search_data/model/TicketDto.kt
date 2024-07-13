package com.rud.tickets_search_data.model

import com.google.gson.annotations.SerializedName

data class TicketDto(
    val arrival: Arrival,
    val badge: String,
    val company: String,
    val departure: Departure,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    val id: Int,
    val luggage: Luggage,
    val price: Price,
    @SerializedName("provider_name")
    val providerName: String
)