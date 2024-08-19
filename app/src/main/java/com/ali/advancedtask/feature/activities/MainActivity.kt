package com.ali.advancedtask.feature.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.ali.advancedtask.R
import com.ali.advancedtask.YajhazApplication
import com.ali.advancedtask.core.user_manager.UserHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userHandler: UserHandler

    companion object {
        fun showToast(message: String) {
            Toast.makeText(YajhazApplication.getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (!userHandler.getUserToken().isNullOrEmpty()) {
            navController.navigate(R.id.homeFragment, null, getNavOptions(R.id.homeFragment))
        } else {
            navController.navigate(R.id.logInFragment, null, getNavOptions(R.id.logInFragment))
        }
    }

    private fun getNavOptions(destinationId: Int): NavOptions {
        return NavOptions.Builder()
            .setPopUpTo(destinationId, true)
            .setLaunchSingleTop(true)
            .build()
    }
}