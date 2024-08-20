package com.rud.tickets_search_data.model

import com.google.gson.annotations.SerializedName

internal data class Luggage(
    @SerializedName("has_luggage")
    val hasLuggage: Boolean,
    val price: Price
)