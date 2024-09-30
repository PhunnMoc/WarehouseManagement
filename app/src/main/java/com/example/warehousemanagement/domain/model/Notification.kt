package com.example.warehousemanagement.domain.model

import java.util.Date

data class Notification(
    val idNotification: String,
    val title: String,
    val description: String? = null,
    val type: NotificationType,
    val timestamp: Date
)