package com.ali.advancedtask.presentation.signupscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.databinding.FragmentSignUpBinding
import com.ali.advancedtask.model.User
import com.ali.advancedtask.presentation.UsersViewModel
import com.google.android.material.button.MaterialButton

class SignUpFragment : Fragment() {
    private val vm: UsersViewModel by activityViewModels()
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
            if(enteredPassword != enteredConfirmedPassword){
                Toast.makeText(requireContext(),"The entered password must be the same !", Toast.LENGTH_LONG).show()
            }else{
                val newUser = User(10,enteredName,enteredEmail,enteredPhoneNumber,enteredPassword)
                vm.addUser(newUser)
                navToLogInScreen()
            }

        }

        //Go To LogIn Screen
        val logInJump =  binding.fragmentSignupTvLogin
        logInJump.setOnClickListener{
            navToLogInScreen()
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