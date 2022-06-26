package id.agunggum.gamedirectory.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingPreferences: SettingPreferences
) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return settingPreferences.isDarkTheme.asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingPreferences.setIsDarkTheme(isDarkModeActive)
        }
    }
}