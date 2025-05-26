package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class MessageChatBoxResponse(
    @SerialName("message") val message: String
)