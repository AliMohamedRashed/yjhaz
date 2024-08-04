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


    companion object {
        fun showToast(message: String) {
            Toast.makeText(YajhazApplication.getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    @Inject
    lateinit var userHandler: UserHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Check the checkbox value
        val isCheckBoxChecked = userHandler.getCheckBoxState()

        // Determine the start destination based on the checkbox state
        if (isCheckBoxChecked == true) {
            // Navigate to HomeFragment if checkbox is checked
            navController.navigate(R.id.homeFragment, null, getNavOptions(R.id.homeFragment))
        } else {
            // Navigate to LogInFragment if checkbox is not checked
            navController.navigate(R.id.logInFragment, null, getNavOptions(R.id.logInFragment))
        }
    }

    private fun getNavOptions(destinationId: Int): NavOptions {
        return NavOptions.Builder()
            .setPopUpTo(destinationId, true) // Clear the back stack up to the destinationId
            .setLaunchSingleTop(true) // Ensure the destination is the top of the stack
            .build()
    }
}