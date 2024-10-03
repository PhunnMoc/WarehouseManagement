package com.example.warehousemanagement.domain.model

import java.util.Date

data class Notification(
    val id: String,
    val title: String,
    val senderId: String,
    val receiverId: String,
    val description: String? = null,
    val type: NotificationType,
    val timestamp: Date
)