package com.ali.advancedtask.feature.signup.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.R
import com.ali.advancedtask.core.storge_manager.StorageHandler
import com.ali.advancedtask.databinding.FragmentSignUpBinding
import com.ali.advancedtask.feature.activities.MainActivity
import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.domain.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val signUpViewModel: SignUpViewModel by viewModels()

    private var _binding: FragmentSignUpBinding? = null
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
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Sign up a new USER
        viewLifecycleOwner.lifecycleScope.launch {
            signUpViewModel.state.collect { state ->
                //A problem with progress bar not showing should be fixed
                binding.fragmentSignupProgressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                if (state.success) {
                    navToDestination(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment())
                }
                state.error?.let { MainActivity.showToast(it) }
            }
        }
        binding.fragmentSignupBtnSignup.setOnClickListener {
            val enteredName = binding.fragmentSignupEtName.text.toString()
            val enteredEmail = binding.fragmentSignupEtEmail.text.toString()
            val enteredPhoneNumber = binding.fragmentSignupEtPhoneNumber.text.toString()
            val enteredPassword = binding.fragmentSignupEtPassword.text.toString()
            val enteredConfirmedPassword = binding.fragmentSignupEtConfirmPassword.text.toString()
            if (enteredPassword == enteredConfirmedPassword) {
                if (enteredName.isNotEmpty() && enteredEmail.isNotEmpty() && enteredPhoneNumber.isNotEmpty()) {
                    val newUser = SignUpRequestDto(enteredName, enteredEmail, enteredPhoneNumber, enteredPassword)
                    signUpViewModel.registerNewUser(newUser)
                } else {
                    MainActivity.showToast("All fields must be filled!")
                }
            } else {
                MainActivity.showToast("The entered password must be the same!")
            }
        }

        //Go To LogIn Screen
        binding.fragmentSignupTvLogin.setOnClickListener{
            navToDestination(SignUpFragmentDirections.actionSignUpFragmentToLogInFragment())
        }
    }

    private fun navToDestination(action: NavDirections){
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.signUpFragment, true)
            .setLaunchSingleTop(true)
            .build()
        mNavController.navigate(action,navOptions)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}