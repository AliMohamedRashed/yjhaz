package com.ali.advancedtask.presentation.user

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.domain.model.User
import com.ali.advancedtask.databinding.FragmentLogInBinding
import com.ali.advancedtask.domain.viewmodel.user_viewmodel.UsersViewModel
import com.ali.advancedtask.presentation.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class LogInFragment : Fragment() {
    private val vm: UsersViewModel by viewModels()
    private var users: List<User>? =null
    //Global Variables
    private lateinit var logInButton: MaterialButton
    private lateinit var signUpBtn: MaterialTextView
    private lateinit var action: NavDirections
    private var validEmailAndPassword: Boolean = false
    private lateinit var user: User

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

        vm.users.observe(viewLifecycleOwner){
            users = it
        }

        //Go to Home Screen
        logInButton = binding.fragmentLoginBtnLogIn
        signUpBtn = binding.fragmentLoginTvSignUp

        logInButton.setOnClickListener {

            val enteredEmail = binding.fragmentLoginEtEmail.text.toString()
            val enteredPassword = binding.fragmentLoginEtPassword.text.toString()

            if (validateInputs(enteredEmail, enteredPassword)) {
                users?.forEach {
                    if (it.email == enteredEmail && it.password == enteredPassword) {
                        validEmailAndPassword = true
                        user = it
                    }
                }
                if (validEmailAndPassword){
                    action = LogInFragmentDirections.actionLogInFragmentToHomeFragment(user)
                    mNavController.navigate(action)
                }else{
                    MainActivity.showToast("Incorrect Email or Password !!")
                }
            }
        }

        //Go to Sign Up Screen
        signUpBtn.setOnClickListener {
            action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
            mNavController.navigate(action)
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