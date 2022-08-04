package com.soul.suhrob.soul

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.android.support.DaggerAppCompatActivity
import com.soul.suhrob.soul.databinding.ActivityMainBinding
import com.soul.suhrob.soul.utils.statusBarColor


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        statusBarColor(
            ResourcesCompat.getColor(
                resources, R.color.color_page, theme
            ), ResourcesCompat.getColor(
                resources, R.color.color_page, theme
            ), false
        )

        navController = findNavController(R.id.main_nav_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.splashFragment,
                R.id.mainFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id==R.id.splashFragment){
                binding.appBar.visibility= View.GONE
            }else{
                binding.appBar.visibility= View.VISIBLE
                statusBarColor(
                    ResourcesCompat.getColor(
                        resources, R.color.color_page, theme
                    ), ResourcesCompat.getColor(
                        resources, R.color.color_page, theme
                    ), false
                )
                binding.toolbar.setSubtitleTextColor(Color.WHITE)
                binding.toolbar.setTitleTextColor(Color.WHITE)
                binding.toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleStyle)
                val upArrow = ResourcesCompat.getDrawable(
                    resources, R.drawable.ic_baseline_arrow_back_24, theme
                )
                supportActionBar!!.setHomeAsUpIndicator(upArrow)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}