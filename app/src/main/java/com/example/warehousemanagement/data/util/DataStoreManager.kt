package com.example.warehousemanagement.data.util

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val userPreferences: DataStore<Preferences>
) {
    private object PreferencesKeys {
        val tokenMap = stringPreferencesKey("token_map")
    }

    private val gson = Gson()

    suspend fun saveTokenMap(tokenMap: Map<String, String>) {
        try {
            val json = gson.toJson(tokenMap)
            userPreferences.edit { preferences ->
                preferences[PreferencesKeys.tokenMap] = json
            }
        } catch (ioException: IOException) {
            Log.e("Data store", "Fail")
        }
    }

    suspend fun clearTokenMap() {
        try {
            userPreferences.edit { preferences ->
                preferences.remove(PreferencesKeys.tokenMap)
            }
        } catch (ioException: IOException) {
            Log.e("Data store", "Failed to clear token_map")
        }
    }

    val tokenMap: Flow<Map<String, String>> = userPreferences.data
        .map { preferences ->
            val json = preferences[PreferencesKeys.tokenMap] ?: "{}"
            val type = object : TypeToken<Map<String, String>>() {}.type
            gson.fromJson(json, type)
        }
}
