package id.agunggum.gamedirectory.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import id.agunggum.gamedirectory.R
import id.agunggum.gamedirectory.databinding.ActivitySettingBinding

@AndroidEntryPoint
class SettingActivity : AppCompatActivity() {

    private val settingViewModel: SettingViewModel by viewModels()

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get value from Flow using the firstOrNull() operator
        settingViewModel.getThemeSettings().observe(
            this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }
        // set dark theme
        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }

    }
}