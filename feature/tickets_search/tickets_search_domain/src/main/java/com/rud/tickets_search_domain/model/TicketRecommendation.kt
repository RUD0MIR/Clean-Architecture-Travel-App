package com.rud.tickets_search_domain.model

class TicketRecommendation(
    val id: Int,
    val price: Int,
    val timeRange: List<String>,
    val title: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TicketRecommendation

        if (id != other.id) return false
        if (price != other.price) return false
        if (timeRange != other.timeRange) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + price
        result = 31 * result + timeRange.hashCode()
        result = 31 * result + title.hashCode()
        return result
    }
}