package com.example.warehousemanagement.data.network.dto

import com.example.warehousemanagement.data.mapper.convertToModel
import com.example.warehousemanagement.data.mapper.convertToResponse
import com.example.warehousemanagement.domain.model.Product

data class ExportProductDto(
    val productId: String,
    val quantity: Int
)

data class ExportProductResponse(
    val product: ProductResponse,
    val quantity: Int
)

fun List<ExportProductResponse>.convertToResponse(): Map<Product, Int> {
    val map = mutableMapOf<Product, Int>()
    this.forEach {
        map[it.product.convertToModel()] = it.quantity
    }
    return map
}

fun Map<Product, Int>.convertToResponse(): List<ExportProductResponse> {
    return this.map {
        ExportProductResponse(
            product = it.key.convertToResponse(),
            quantity = it.value
        )
    }
}