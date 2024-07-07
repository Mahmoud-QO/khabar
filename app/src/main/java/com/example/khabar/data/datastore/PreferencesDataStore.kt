package com.example.khabar.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "preferences"

private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = DATASTORE_NAME
)

class PreferencesDataStore(private val context: Context)
{
    fun getOnboardingCompleted(): Flow<Boolean> =
        context.preferencesDataStore.data.map { it[ONBOARDING_COMPLETED_KEY] ?: false }

    suspend fun setOnboardingCompleted(value: Boolean) {
        context.preferencesDataStore.edit { it[ONBOARDING_COMPLETED_KEY] = value }
    }

    companion object {
        private val ONBOARDING_COMPLETED_KEY = booleanPreferencesKey("onboarding_completed")
    }
}