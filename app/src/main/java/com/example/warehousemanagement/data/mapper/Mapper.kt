package com.example.warehousemanagement.data.mapper

import com.example.warehousemanagement.data.network.dto.AddressResponse
import com.example.warehousemanagement.data.network.dto.CustomerResponse
import com.example.warehousemanagement.data.network.dto.ExportPackageResponse
import com.example.warehousemanagement.data.network.dto.GenreResponse
import com.example.warehousemanagement.data.network.dto.ImportPackageResponseItem
import com.example.warehousemanagement.data.network.dto.ProductResponse
import com.example.warehousemanagement.data.network.dto.ReceiverResponse
import com.example.warehousemanagement.data.network.dto.StorageLocationResponse
import com.example.warehousemanagement.data.network.dto.SupplierResponse
import com.example.warehousemanagement.data.network.dto.UserResponse
import com.example.warehousemanagement.domain.model.Address
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.ExportPackages
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.model.User

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
    if (_id == null || name == null || email == null || address == null) {
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

fun CustomerResponse.convertToModel(): Customer? {
    if (id == null || customerName == null || email == null || address == null) {
        return null
    }
    return Customer(
        idCustomer = id,
        customerName = customerName,
        email = email,
        address = address.convertToModel() ?: return null,
    )
}

fun Customer.convertToResponse(): CustomerResponse {

    return CustomerResponse(
        id = idCustomer,
        customerName = customerName,
        email = email,
        address = address.convertToResponse(),
    )
}

fun AddressResponse.convertToModel(): Address? {
    if (postalCode == null || phone == null) {
        return null
    }
    return Address(
        street = street ?: "",
        district = district ?: "",
        city = city ?: "",
        postalCode = postalCode,
        phone = phone,
    )
}

fun Address.convertToResponse(): AddressResponse {
    return AddressResponse(
        street = street ?: "",
        district = district ?: "",
        city = city ?: "",
        postalCode = postalCode,
        phone = phone,
    )
}

fun ImportPackageResponseItem.convertToModel(): ImportPackages? {
    if (id == null || packageName == null || receiver == null || importDate == null) {
        return null
    }
    return ImportPackages(
        idImportPackages = id,
        packageName = packageName,
        importDate = importDate,
        listProducts = listProducts!!.mapNotNull { it.convertToModel() },
        statusDone = statusDone ?: false,
        note = note ?: "",
        receiver = receiver,
    )
}

fun ImportPackages.convertToResponse(): ImportPackageResponseItem {
    return ImportPackageResponseItem(
        id = idImportPackages,
        importDate = importDate,
        listProducts = listProducts.map { it.convertToResponse() },
        note = note,
        packageName = packageName,
        receiver = receiver,
        statusDone = statusDone ?: false,
    )
}

fun ReceiverResponse.convertToModel(): User? {
    if (_id == null || information == null) {
        return null
    }
    return User(
        idUser = _id,
        username = username ?: "",
        passwordHash = passwordHash ?: "",
        information = information,
    )
}

fun UserResponse.convertToModel(): User? {
    if (_id == null || username == null || passwordHash == null || information == null) {
        return null
    }
    return User(
        idUser = _id,
        username = username ?: "",
        passwordHash = passwordHash ?: "",
        information = information,
    )
}


fun ExportPackageResponse.convertToModel(): ExportPackages? {
    if (id == null || listProducts == null || sender == null || exportDate == null) {
        return null
    }
    return ExportPackages(
        idExportPackages = id,
        packageName = packageName ?: "Package Name",
        listProduct = listProducts.mapNotNull { it.convertToModel() },
        exportDate = exportDate,
        customer = customer?.convertToModel()!!,
        status = statusDone ?: false,
        deliveryMethod = deliveryMethod ?: "",
        note = note,
        sender = sender.convertToModel()!!,
    )
}