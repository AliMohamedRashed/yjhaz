package com.ali.advancedtask.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.advancedtask.R
import com.ali.advancedtask.data.SharedPreferencesHelper
import com.ali.advancedtask.databinding.FragmentHomeBinding
import com.ali.advancedtask.presentation.home.adapters.CategoryAdapter
import com.ali.advancedtask.presentation.home.adapters.PopularAdapter
import com.ali.advancedtask.presentation.home.adapters.TrendingAdapter
import com.ali.advancedtask.domain.viewmodel.home_viewmodel.CategoryViewModel
import com.ali.advancedtask.domain.viewmodel.home_viewmodel.PopularViewModel
import com.ali.advancedtask.domain.viewmodel.home_viewmodel.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoryAdapter
    private val trendingViewModel: TrendingViewModel by viewModels()
    private lateinit var trendingAdapter: TrendingAdapter
    private val popularViewModel: PopularViewModel by viewModels()
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var action: NavDirections
    private lateinit var mNavController: NavController
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var userName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mNavController = findNavController()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        arguments?.let {
            userName = HomeFragmentArgs.fromBundle(it).userName
        }
        val getUserName = SharedPreferencesHelper.getUserName(requireContext())
        if(getUserName !=null) {binding.fragmentHomeTvUserName.text = "Hello ${getUserName?.split(" ")?.first()}"}
        else {binding.fragmentHomeTvUserName.text = "Hello ${userName.split(" ").first()}"}

        categoriesAdapter = CategoryAdapter(emptyList())
        binding.fragmentHomeRvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvCategory.adapter = categoriesAdapter
        categoryViewModel.categories.observe(viewLifecycleOwner) { categoryList ->
            categoriesAdapter.updateData(categoryList)
        }

        trendingAdapter = TrendingAdapter(emptyList())
        binding.fragmentHomeRvTrending.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvTrending.adapter = trendingAdapter
        trendingViewModel.trending.observe(viewLifecycleOwner) { trendingList ->
            trendingAdapter.updateData(trendingList)
        }

        popularAdapter = PopularAdapter(emptyList())
        binding.fragmentHomeRvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvPopular.adapter = popularAdapter
        popularViewModel.popular.observe(viewLifecycleOwner) { popularList ->
            popularAdapter.updateData(popularList)
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logOut = binding.fragmentHomeIvBackButton
        logOut.setOnClickListener {
            showLogoutConfirmationDialog()
        }

    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setMessage("Do you really want to log out?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            // Perform logout action here
            SharedPreferencesHelper.removeUserId(requireContext())
            SharedPreferencesHelper.removeUserName(requireContext())
            SharedPreferencesHelper.removeCheckBoxState(requireContext())
            action = HomeFragmentDirections.actionHomeFragmentToLogInFragment()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, true)
                .setLaunchSingleTop(true)
                .build()
            mNavController.navigate(action,navOptions)
            dialog.dismiss()
            // For example, navigate to login screen or clear user data
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}