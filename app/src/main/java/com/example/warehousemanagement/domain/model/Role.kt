package com.example.warehousemanagement.domain.model

enum class Role(val value: String) {
    USER("User"),
    ADMIN("Admin")
}

fun String.convertToRole(): Role{
    return when(this){
        "ADMIN"-> Role.ADMIN
        else->Role.USER
    }
}