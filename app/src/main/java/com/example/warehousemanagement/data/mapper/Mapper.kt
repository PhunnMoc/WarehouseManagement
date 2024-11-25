package com.example.warehousemanagement.data.mapper

import com.example.warehousemanagement.data.network.dto.AddressResponse
import com.example.warehousemanagement.data.network.dto.GenreResponse
import com.example.warehousemanagement.data.network.dto.ProductResponse
import com.example.warehousemanagement.data.network.dto.StorageLocationResponse
import com.example.warehousemanagement.data.network.dto.SupplierResponse
import com.example.warehousemanagement.domain.model.Address
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier

fun ProductResponse.convertToModel(): Product? {
    if (id == null || description == null || genre == null || image == null || importPrice == null || inStock == null || productName == null || quantity == null || sellingPrice == null || storageLocation == null || supplier == null) {
        return null
    }
    return Product(
        idProduct = id,
        description = description,
        genre = genre.convertToModel() ?: return null,
        image = image,
        importPrice = importPrice,
        inStock = inStock,
        lastUpdated = lastUpdated ?: "",
        productName = productName,
        quantity = quantity,
        sellingPrice = sellingPrice,
        storageLocation = storageLocation.convertToModel() ?: return null,
        supplier = supplier.convertToModel() ?: return null,
    )

}

fun Product.convertToResponse(): ProductResponse {
    return ProductResponse(
        id = idProduct,
        description = description,
        genre = genre.convertToResponse(),
        image = image,
        importPrice = importPrice,
        inStock = inStock,
        lastUpdated = lastUpdated ?: "",
        productName = productName,
        quantity = quantity,
        sellingPrice = sellingPrice,
        storageLocation = storageLocation.convertToResponse(),
        supplier = supplier.convertToResponse(),
    )

}


fun GenreResponse.convertToModel(): Genre? {
    if (_id == null || genreName == null) {
        return null
    }
    return Genre(
        idGenre = _id,
        genreName = genreName,
    )
}

fun Genre.convertToResponse(): GenreResponse {
    return GenreResponse(
        _id = idGenre,
        genreName = genreName,
    )
}

fun StorageLocationResponse.convertToModel(): StorageLocation? {
    if (_id == null || storageLocationName == null) {
        return null
    }
    return StorageLocation(
        id = _id,
        storageLocationName = storageLocationName,
        storageLocationImage = storageLocationImage,
    )
}

fun StorageLocation.convertToResponse(): StorageLocationResponse {
    return StorageLocationResponse(
        _id = id,
        storageLocationName = storageLocationName,
        storageLocationImage = storageLocationImage,
    )
}

fun SupplierResponse.convertToModel(): Supplier? {
    if (
        _id == null ||
        name == null ||
        email == null ||
        address == null
    ) {
        return null
    }
    return Supplier(
        idSupplier = _id,
        name = name,
        email = email,
        address = address.convertToModel() ?: return null,
        ratings = ratings,
    )
}

fun Supplier.convertToResponse(): SupplierResponse {

    return SupplierResponse(
        _id = idSupplier,
        name = name,
        email = email,
        address = address.convertToResponse(),
        ratings = ratings,
    )
}

fun AddressResponse.convertToModel(): Address? {
    if (
        idAddress == null ||
        postalCode == null ||
        phone == null
    ) {
        return null
    }
    return Address(
        idAddress = idAddress,
        street = street ?: "",
        district = district ?: "",
        city = city ?: "",
        postalCode = postalCode,
        phone = phone,
    )
}

fun Address.convertToResponse(): AddressResponse {
    return AddressResponse(
        idAddress = idAddress,
        street = street ?: "",
        district = district ?: "",
        city = city ?: "",
        postalCode = postalCode,
        phone = phone,
    )
}

