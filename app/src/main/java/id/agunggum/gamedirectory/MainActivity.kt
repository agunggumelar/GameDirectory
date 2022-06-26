package id.agunggum.gamedirectory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import id.agunggum.gamedirectory.databinding.ActivityMainBinding
import id.agunggum.gamedirectory.setting.SettingActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupBottomNav()
    }

    private fun setupBottomNav() {
        val bottomNavigationView = binding?.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        if (bottomNavigationView != null) {
            NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.navController
            )

            val navController = navHostFragment.navController
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment -> {
                        showBottomNav()
                        supportActionBar?.title = getString(R.string.app_name)
                        supportActionBar?.show()
                    }
                    R.id.favoriteFragment -> {
                        showBottomNav()
                        supportActionBar?.title = getString(R.string.favorites)
                        supportActionBar?.show()
                    }
                    else -> {
                        hideBottomNav()
                        supportActionBar?.hide()
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting_menu -> {
                navSetting()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navSetting(){
        val intent = Intent(this@MainActivity, SettingActivity::class.java)
        startActivity(intent)
    }

    private fun showBottomNav() {
        binding?.bottomNav?.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding?.bottomNav?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }
}