package id.agunggum.gamedirectory.setting

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("settings")

interface PreferenceStorage {
    val isDarkTheme: Flow<Boolean>
    suspend fun setIsDarkTheme(isDarkTheme: Boolean)

    suspend fun clearPreferenceStorage()
}

@Singleton
class SettingPreferences @Inject constructor(
    @ApplicationContext context: Context
) : PreferenceStorage {

    private val Context._dataStore by preferencesDataStore("app_preferences")
    private val dataStore : DataStore<Preferences> = context._dataStore

    private object PreferencesKeys {
        val IS_DARK_THEME = booleanPreferencesKey("pref_dark_theme")
    }

    override val isDarkTheme: Flow<Boolean>
        get() = dataStore.getValueAsFlow(PreferencesKeys.IS_DARK_THEME, false)

    override suspend fun clearPreferenceStorage() {
        dataStore.edit {
            it.clear()
        }
    }

    override suspend fun setIsDarkTheme(isDarkTheme: Boolean) {
        dataStore.setValue(PreferencesKeys.IS_DARK_THEME, isDarkTheme)
    }

    /***
     * handy function to save key-value pairs in Preference. Sets or updates the value in Preference
     * @param key used to identify the preference
     * @param value the value to be saved in the preference
     */
    private suspend fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        this.edit { preferences ->
            // save the value in prefs
            preferences[key] = value
        }
    }

    /***
     * handy function to return Preference value based on the Preference key
     * @param key  used to identify the preference
     * @param defaultValue value in case the Preference does not exists
     * @throws Exception if there is some error in getting the value
     * @return [Flow] of [T]
     */
    private fun <T> DataStore<Preferences>.getValueAsFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return this.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                // we try again to store the value in the map operator
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // return the default value if it doesn't exist in the storage
            preferences[key] ?: defaultValue
        }
    }
}