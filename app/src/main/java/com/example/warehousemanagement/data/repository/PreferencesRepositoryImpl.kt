package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.util.DataStoreManager
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : PreferencesRepository {
    override fun getAccessToken(): Flow<String> {
        return dataStoreManager.tokenMap.map { it["token"] ?: "" }
    }

    override fun getUserId(): Flow<String> {
        return dataStoreManager.tokenMap.map { it["id"] ?: "" }
    }

    override fun getUserRole(): Flow<String> {
        return dataStoreManager.tokenMap.map { it["role"] ?: "" }
    }

    override suspend fun setCurrentUser(currentUser: Map<String, String>) {
        return dataStoreManager.saveTokenMap(currentUser)
    }

    override suspend fun deleteUser() {
        return dataStoreManager.clearTokenMap()
    }
}