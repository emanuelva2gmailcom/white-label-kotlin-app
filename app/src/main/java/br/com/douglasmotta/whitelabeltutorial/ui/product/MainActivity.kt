package br.com.douglasmotta.whitelabeltutorial.ui.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.databinding.ActivityMainBinding
import br.com.douglasmotta.whitelabeltutorial.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        replaceFragment(R.id.homeFragment)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_navitem -> replaceFragment(R.id.homeFragment)
                R.id.products_navitem -> replaceFragment(R.id.productsFragment)
                R.id.settings_navitem -> replaceFragment(R.id.settingsFragment)
            }
            true
        }

        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)
    }

    private fun replaceFragment(fragmentId: Int) {
        navController.navigate(fragmentId)
    }
}