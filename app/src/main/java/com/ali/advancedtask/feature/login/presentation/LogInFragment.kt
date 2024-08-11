package com.ali.advancedtask.feature.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.R
import com.ali.advancedtask.databinding.FragmentLogInBinding
import com.ali.advancedtask.feature.activities.MainActivity
import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.domin.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : Fragment() {
    private val loginViewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNavController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.state.collect { state ->
                //A problem with progress bar not showing should be fixed
                if(state.success) {binding.fragmentLoginProgressBar.visibility = View.VISIBLE}
                if (state.success) {
                    navToDestination(LogInFragmentDirections.actionLogInFragmentToHomeFragment(state.response.data!!.name))
                }
                state.error?.let { MainActivity.showToast(it) }
                Log.d("User data", state.response.toString())
            }
        }

        //Go to Home Screen
        binding.fragmentLoginBtnLogIn.setOnClickListener {
            val enteredEmail = binding.fragmentLoginEtEmail.text.toString()
            val enteredPassword = binding.fragmentLoginEtPassword.text.toString()
            if (enteredEmail.isNotEmpty() && enteredPassword.isNotEmpty()) {
                val loginData = LoginRequestDto(enteredEmail,enteredPassword)
                loginViewModel.getUserLoggedIn(loginData)
            } else {
                MainActivity.showToast("All fields must be filled!")
            }
        }

        //Go to Sign Up Screen
        binding.fragmentLoginTvSignUp.setOnClickListener {
            navToDestination(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())

        }

    }
    private fun navToDestination(action: NavDirections){
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.logInFragment, true)
            .setLaunchSingleTop(true)
            .build()
        mNavController.navigate(action, navOptions)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}