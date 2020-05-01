package com.gzeinnumer.myandroidtemplate3kt.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.base.BaseActivity
import com.gzeinnumer.myandroidtemplate3kt.databinding.ActivityMainBinding
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD

class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val func = "onCreate+"
        myLogD(TAG,func)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()
        initFab()
        initNavigation()
    }

    private fun initToolBar() {
        val func = "initToolBar+"
        myLogD(TAG,func)

        setSupportActionBar(binding.appBarMain.toolbar)
    }

    private fun initFab() {
        val func = "initFab+"
        myLogD(TAG,func)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun initNavigation() {
        val func = "initNavigation+"
        myLogD(TAG,func)

        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_profile,
                R.id.nav_post
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
