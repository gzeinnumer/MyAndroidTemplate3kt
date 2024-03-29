package com.gzeinnumer.myandroidtemplate3kt.ui.main

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.base.BaseActivity
import com.gzeinnumer.myandroidtemplate3kt.dagger.ViewModelProviderFactory
import com.gzeinnumer.myandroidtemplate3kt.databinding.ActivityMainBinding
import com.gzeinnumer.myandroidtemplate3kt.ui.auth.AuthActivity
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import kotlinx.android.synthetic.main.nav_header_main.view.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val func = "onCreate+"
        myLogD(TAG,func)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this, providerFactory)[MainVM::class.java]

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
                R.id.nav_post,
                R.id.nav_post_local
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout->{
                viewModel.logout()
                viewModel.cekSession().observe(this, Observer {
                    if (!it) {
                        startActivity(Intent(applicationContext, AuthActivity::class.java))
                        finish()
                        onTransision()
                    }
                })
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
