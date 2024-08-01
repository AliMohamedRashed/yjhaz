package com.ali.advancedtask.presentation.user

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.R
import com.ali.advancedtask.data.SharedPreferencesHelper
import com.ali.advancedtask.databinding.FragmentLogInBinding
import com.ali.advancedtask.domain.viewmodel.user_viewmodel.UsersViewModel
import com.ali.advancedtask.presentation.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {
    private val vm: UsersViewModel by viewModels()
    private lateinit var logInButton: MaterialButton
    private lateinit var signUpBtn: MaterialTextView
    private lateinit var keepMeLoggedInCB: CheckBox
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keepMeLoggedInCB = binding.fragmentLoginCbKeepMeLoggedIn

        //Go to Home Screen
        logInButton = binding.fragmentLoginBtnLogIn
        signUpBtn = binding.fragmentLoginTvSignUp

        logInButton.setOnClickListener {

            val enteredEmail = binding.fragmentLoginEtEmail.text.toString()
            val enteredPassword = binding.fragmentLoginEtPassword.text.toString()

            if (validateInputs(enteredEmail, enteredPassword)) {
                vm.getUser(enteredEmail, enteredPassword)
            }
        }
        vm.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                if(keepMeLoggedInCB.isChecked){
                    SharedPreferencesHelper.saveUserId(requireContext(),user.id.toString())
                    SharedPreferencesHelper.saveUserName(requireContext(),user.name)
                    SharedPreferencesHelper.saveCheckBoxState(requireContext(),true)
                }else{
                    SharedPreferencesHelper.saveCheckBoxState(requireContext(),false)
                }
                val action = LogInFragmentDirections.actionLogInFragmentToHomeFragment(userName = user.name)
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.logInFragment,true)
                    .setLaunchSingleTop(true)
                    .build()
                mNavController.navigate(action,navOptions)
            } else {
                MainActivity.showToast("Incorrect Email or Password !!")
            }
        }

        //Go to Sign Up Screen
        signUpBtn.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.logInFragment,true)
                .setLaunchSingleTop(true)
                .build()
            mNavController.navigate(action,navOptions)
        }

    }

    private fun validateInputs(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                MainActivity.showToast("Email cannot be empty")
                false
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                MainActivity.showToast("Invalid email format")
                false
            }

            TextUtils.isEmpty(password) -> {
                MainActivity.showToast("Password cannot be empty")
                false
            }

            password.length < 8 -> {
                MainActivity.showToast("Password must be at least 8 characters long")
                false
            }

            else -> true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}