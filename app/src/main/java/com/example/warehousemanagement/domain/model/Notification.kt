package com.example.warehousemanagement.domain.model

import java.util.Date

data class Notification(
    val id: String,
    val title: String,
    val description: String,
    val type: String,
    val timestamp: Date
)