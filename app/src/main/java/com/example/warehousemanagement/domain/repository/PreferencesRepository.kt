package com.example.warehousemanagement.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getAccessToken(): Flow<String>
    fun getUserId(): Flow<String>
    fun getUserRole(): Flow<String>
    suspend fun setCurrentUser(currentUser: Map<String, String>)
    suspend fun deleteUser()
}