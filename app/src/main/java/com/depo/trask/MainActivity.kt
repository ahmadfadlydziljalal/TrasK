package com.depo.trask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.depo.trask.ui.login.LoginListener
import com.depo.trask.util.toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private val navigationInMainScreen = setOf(
        R.id.loginFragment,
        R.id.homeFragment,
        R.id.containerShippingFragment,
        R.id.locationFragment,
        R.id.historyFragment,
        R.id.aboutFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavController()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

    // Nav Controller
    private fun setupNavController() {

        navController = findNavController(R.id.nav_host_fragment)
        setupActionBar()
        setupBottomNavigationBar()

        navController.addOnDestinationChangedListener { _, destination, _ ->

            // Listening toolbar
            when(destination.id){
                R.id.loginFragment, R.id.aboutFragment -> toolbar.visibility = View.GONE
                else -> toolbar.visibility = View.VISIBLE
            }

            // Listening bottom navigation bar
            when (destination.id) {
                R.id.loginFragment, R.id.aboutFragment -> bottomNavigationView.visibility = View.GONE
                else -> bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    // Top Bar OR App Bar
    private fun setupActionBar() {

        // Ini menampilakan tanda panah pada app bar
        // val appBarConfiguration = AppBarConfiguration(navController.graph)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val appBarConfiguration = AppBarConfiguration(
            navigationInMainScreen
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    // Bottom Navigation Bar
    private fun setupBottomNavigationBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setupWithNavController(navController)
    }

}