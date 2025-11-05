package com.example.chatmate.ui.theme

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemeViewModel(context: Context) : ViewModel() {
    private val dataStore = context.dataStore

    companion object {
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    }

    val isDarkTheme = dataStore.data.map {
        it[DARK_THEME_KEY] ?: false
    }

    fun toggleTheme() {
        viewModelScope.launch {
            dataStore.edit {
                val current = it[DARK_THEME_KEY] ?: false
                it[DARK_THEME_KEY] = !current
            }
        }
    }
}