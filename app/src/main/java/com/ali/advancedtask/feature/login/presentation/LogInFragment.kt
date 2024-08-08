package com.ali.advancedtask.feature.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.R
import com.ali.advancedtask.databinding.FragmentLogInBinding
import com.ali.advancedtask.feature.activities.MainActivity
import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.domin.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        observeSignUpState()

        //Go to Home Screen
        binding.fragmentLoginBtnLogIn.setOnClickListener {

            val enteredEmail = binding.fragmentLoginEtEmail.text.toString()
            val enteredPassword = binding.fragmentLoginEtPassword.text.toString()

            val loginData = LoginRequestDto(enteredEmail,enteredPassword)
            loginViewModel.getUserLoggedIn(loginData)
        }

        //Go to Sign Up Screen
        binding.fragmentLoginTvSignUp.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.logInFragment, true)
                .setLaunchSingleTop(true)
                .build()
            mNavController.navigate(action, navOptions)
        }

    }
    private fun observeSignUpState() {
        val screenState = loginViewModel.state.value

        if (!screenState.response.success) {
            MainActivity.showToast(screenState.response.message)
        }
    }
//    private fun validateInputs(email: String, password: String): Boolean {
//        return when {
//            TextUtils.isEmpty(email) -> {
//                MainActivity.showToast("Email cannot be empty")
//                false
//            }
//
//            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
//                MainActivity.showToast("Invalid email format")
//                false
//            }
//
//            TextUtils.isEmpty(password) -> {
//                MainActivity.showToast("Password cannot be empty")
//                false
//            }
//
//            password.length < 8 -> {
//                MainActivity.showToast("Password must be at least 8 characters long")
//                false
//            }
//
//            else -> true
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}