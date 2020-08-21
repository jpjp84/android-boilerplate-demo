package com.jp.boilerplate.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.jp.boilerplate.R
import com.jp.boilerplate.databinding.ActivityMainBinding
import com.jp.boilerplate.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(R.id.homeFragment)
            .build()
    }

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override val viewModel by viewModels<MainViewModel>()

    override fun getViewLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(viewBinding.mainToolbar)

        setNavigation()
        viewModel.updateUser()
    }

    private fun setNavigation() {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
