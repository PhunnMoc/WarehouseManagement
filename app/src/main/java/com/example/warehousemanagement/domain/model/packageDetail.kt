package com.example.warehousemanagement.domain.model

import androidx.core.location.LocationRequestCompat.Quality

class packageDetail(
    val idPackageDetail: String,
    val idPackage: String,
    val idProduct: String,
    val quality: Int,
    val unitPrice: Double
) {
}