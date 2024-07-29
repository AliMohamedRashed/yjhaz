package com.ali.advancedtask.presentation.user

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.YajhazApplication
import com.ali.advancedtask.model.User
import com.ali.advancedtask.databinding.FragmentLogInBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class LogInFragment : Fragment() {
    private val vm: UsersViewModel by activityViewModels()
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
                    Log.d("Users Size",users?.size.toString())
                    Toast.makeText(requireContext(),"Incorrect Email or Password !!",Toast.LENGTH_LONG).show()
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
                showToast("Email cannot be empty")
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showToast("Invalid email format")
                false
            }
            TextUtils.isEmpty(password) -> {
                showToast("Password cannot be empty")
                false
            }
            password.length < 8 -> {
                showToast("Password must be at least 8 characters long")
                false
            }
            else -> true
        }
    }
    companion object {
        fun showToast(message: String) {
            Toast.makeText(YajhazApplication.getApplicationContext(), message, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}