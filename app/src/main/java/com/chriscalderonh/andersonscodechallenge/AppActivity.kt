package com.chriscalderonh.andersonscodechallenge

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.chriscalderonh.andersonscodechallenge.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityAppBinding

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        when (destination.id) {
            R.id.searchFragment -> supportActionBar?.hide()
            else -> supportActionBar?.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(findViewById(R.id.tbApp))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}