package com.example.warehousemanagement.domain.model

import java.util.Date

data class Report(
    val idReport: String,
    val timestamp: Date,
    val title: String,
    val idSender: String,
    val reportType: String,
    val description: String? = null,
    val status: String
)