package com.example.tccmobile.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketSearchDto(
    @SerialName("ticketid")
    val ticketId: Int
)
