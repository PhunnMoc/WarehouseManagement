package com.example.warehousemanagement.data.network.dto

import com.example.warehousemanagement.domain.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ProductResponse(
    @SerialName("") val idProduct: String?, // Định danh sản phẩm
    val productName: String?, // Tên sản phẩm
    val genreId: String?, // Loại sản phẩm
    val quantity: Int?, // Số lượng sản phẩm
    val description: String?, // Mô tả sản phẩm
    val importPrice: Double?,
    val sellingPrice: Double?,
    val supplierId: String?, // Thông tin nhà cung cấp
    val isInStock: Boolean?, // Tình trạng hàng hóa
    val barcode: String?, // Mã vạch
    val storageLocationId: String?, // Khu vực
    // val lastUpdated: Date?, // Ngày cập nhật cuối
    val image: String?,
)

fun ProductResponse.convertToModel(): Product? {
    if (idProduct == null) {
        return null
    }
    return Product(
        idProduct = idProduct,
        productName = productName?:"",
        genreId = genreId?:"",
        quantity = quantity?:0,
        description = description?:"",
        importPrice = importPrice?: 0.0,
        sellingPrice = sellingPrice?: 0.0,
        supplierId = supplierId?:"",
        isInStock = isInStock?:false,
        barcode = barcode?:"",
        storageLocationId = storageLocationId?:"",
        image = image?:"",
    )
}