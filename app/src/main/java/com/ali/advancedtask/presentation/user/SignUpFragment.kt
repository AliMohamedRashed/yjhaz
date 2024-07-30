package com.ali.advancedtask.presentation.user

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.databinding.FragmentSignUpBinding
import com.ali.advancedtask.model.User
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val vm: UsersViewModel by viewModels()
    private lateinit var signUpBtn: MaterialButton

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
        signUpBtn = binding.fragmentSignupBtnSignup
        signUpBtn.setOnClickListener {
            val enteredName = binding.fragmentSignupEtName.text.toString()
            val enteredEmail = binding.fragmentSignupEtEmail.text.toString()
            val enteredPhoneNumber = binding.fragmentSignupEtPhoneNumber.text.toString()
            val enteredPassword = binding.fragmentSignupEtPassword.text.toString()
            val enteredConfirmedPassword = binding.fragmentSignupEtConfirmPassword.text.toString()
            if(validateInputs(enteredName,enteredEmail,enteredPhoneNumber,enteredPassword)){
                if(enteredPassword == enteredConfirmedPassword){
                    val newUser = User(null, enteredName,enteredEmail,enteredPhoneNumber,enteredPassword)
                    vm.addUser(newUser)
                    navToLogInScreen()
                }else{
                    LogInFragment.showToast("The entered password must be the same !")
                }
            }
        }

        //Go To LogIn Screen
        val logInJump =  binding.fragmentSignupTvLogin
        logInJump.setOnClickListener{
            navToLogInScreen()
        }
    }
    private fun validateInputs(name: String,email: String,phoneNumber: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                LogInFragment.showToast("Email cannot be empty")
                false
            }
            name.length < 14 -> {
                LogInFragment.showToast("Name must be at least 14 characters long")
                false
            }
            TextUtils.isEmpty(email) -> {
                LogInFragment.showToast("Email cannot be empty")
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                LogInFragment.showToast("Invalid email format")
                false
            }
            TextUtils.isEmpty(phoneNumber) -> {
                LogInFragment.showToast("Email cannot be empty")
                false
            }
            phoneNumber.length < 11 -> {
                LogInFragment.showToast("Phone number must be at least 11 characters long")
                false
            }
            !android.util.Patterns.PHONE.matcher(phoneNumber).matches() -> {
                LogInFragment.showToast("Invalid Phone format")
                false
            }
            TextUtils.isEmpty(password) -> {
                LogInFragment.showToast("Password cannot be empty")
                false
            }
            password.length < 8 -> {
                LogInFragment.showToast("Password must be at least 8 characters long")
                false
            }
            else -> true
        }
    }

    private fun navToLogInScreen(){
        val action = SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
        mNavController.navigate(action)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}