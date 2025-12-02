package com.example.multitasked.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val SHOW_CELEBRATION_KEY = booleanPreferencesKey("show_celebration")
    }

    val showCelebration: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[SHOW_CELEBRATION_KEY] ?: true // Default to true
        }

    suspend fun setShowCelebration(show: Boolean) {
        dataStore.edit {
            it[SHOW_CELEBRATION_KEY] = show
        }
    }
}
