package com.rud.domain.model

data class TicketOffer(
    val id: Int,
    val title: String,
    val timeRange: Array<String>,
    val price: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TicketOffer

        if (id != other.id) return false
        if (title != other.title) return false
        if (!timeRange.contentEquals(other.timeRange)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + timeRange.contentHashCode()
        return result
    }
}