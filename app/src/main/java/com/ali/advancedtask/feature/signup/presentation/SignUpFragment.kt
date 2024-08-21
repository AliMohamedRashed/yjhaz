package com.ali.advancedtask.feature.signup.presentation

import android.app.Dialog
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
import com.ali.advancedtask.core.State
import com.ali.advancedtask.databinding.FragmentSignUpBinding
import com.ali.advancedtask.feature.activities.MainActivity
import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.domain.validation.ValidationResult
import com.ali.advancedtask.feature.signup.domain.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val signUpViewModel: SignUpViewModel by viewModels()

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNavController: NavController

    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNavController = findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        loadingDialog = Dialog(requireContext())
        loadingDialog.setContentView(R.layout.custom_loader_layout)
        loadingDialog.setCancelable(false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            signUpViewModel.state.collect { state ->
                when (state) {
                    is State.Loading -> loadingDialog.show()
                    is State.Success -> {
                        loadingDialog.dismiss()
                        if (state.data.success) {
                            navToDestination(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment())
                        }else{
                            MainActivity.showToast(state.data.message)
                        }
                    }
                    is State.Error -> {
                        loadingDialog.dismiss()
                        MainActivity.showToast(state.exception.message ?: "An error occurred")
                    }
                    null ->  loadingDialog.dismiss()
                }
            }
        }
        binding.fragmentSignupBtnSignup.setOnClickListener {
            val enteredName = binding.fragmentSignupEtName.text.toString()
            val enteredEmail = binding.fragmentSignupEtEmail.text.toString()
            val enteredPhoneNumber = binding.fragmentSignupEtPhoneNumber.text.toString()
            val enteredPassword = binding.fragmentSignupEtPassword.text.toString()
            val enteredConfirmedPassword = binding.fragmentSignupEtConfirmPassword.text.toString()
            val newUser = SignUpRequestDto(enteredName, enteredEmail, enteredPhoneNumber, enteredPassword)
            val validationResult = signUpViewModel.validateInputs(newUser, enteredConfirmedPassword)
            handleValidationResult(validationResult, newUser)
        }

        binding.fragmentSignupTvLogin.setOnClickListener{
            navToDestination(SignUpFragmentDirections.actionSignUpFragmentToLogInFragment())
        }
    }

    private fun handleValidationResult(validationResult: ValidationResult, newUser: SignUpRequestDto) {
        when (validationResult) {
            ValidationResult.SUCCESS -> signUpViewModel.registerNewUser(newUser)
            ValidationResult.EMPTY_NAME -> MainActivity.showToast("Name cannot be empty")
            ValidationResult.NAME_TOO_SHORT -> MainActivity.showToast("Name must be at least 14 characters long")
            ValidationResult.INVALID_EMAIL -> MainActivity.showToast("Invalid email format")
            ValidationResult.EMPTY_PHONE -> MainActivity.showToast("Phone number cannot be empty")
            ValidationResult.PHONE_TOO_SHORT -> MainActivity.showToast("Phone number must be at least 11 characters long")
            ValidationResult.EMPTY_PASSWORD -> MainActivity.showToast("Password cannot be empty")
            ValidationResult.PASSWORD_TOO_SHORT -> MainActivity.showToast("Password must be at least 6 characters long")
            ValidationResult.EMPTY_CONFIRM_PASSWORD -> MainActivity.showToast("Please confirm your password")
            ValidationResult.PASSWORD_MISMATCH -> MainActivity.showToast("Passwords do not match")
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