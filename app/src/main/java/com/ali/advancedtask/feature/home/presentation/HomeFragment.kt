package com.ali.advancedtask.feature.home.presentation

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
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.databinding.FragmentHomeBinding
import com.ali.advancedtask.feature.home.domin.viewmodel.HomeViewModel
import com.ali.advancedtask.feature.home.presentation.adapters.CategoryAdapter
import com.ali.advancedtask.feature.home.presentation.adapters.PopularAdapter
import com.ali.advancedtask.feature.home.presentation.adapters.TrendingAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var trendingAdapter: TrendingAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var action: NavDirections
    private lateinit var mNavController: NavController
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var userName: String

    @Inject
    lateinit var userHandler: UserHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        mNavController = findNavController()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        arguments?.let {
            userName = HomeFragmentArgs.fromBundle(it).userName
        }
        val getUserName = userHandler.getUserName()
        if (getUserName != null) {
            binding.fragmentHomeTvUserName.text = "Hello ${getUserName?.split(" ")?.first()}"
        } else {
            binding.fragmentHomeTvUserName.text = "Hello ${userName.split(" ").first()}"
        }

        categoriesAdapter = CategoryAdapter(emptyList())
        binding.fragmentHomeRvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvCategory.adapter = categoriesAdapter
        viewModel.categories.observe(viewLifecycleOwner) { categoryList ->
            categoriesAdapter.updateData(categoryList)
        }

        trendingAdapter = TrendingAdapter(emptyList())
        binding.fragmentHomeRvTrending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvTrending.adapter = trendingAdapter
        viewModel.trending.observe(viewLifecycleOwner) { trendingList ->
            trendingAdapter.updateData(trendingList)
        }

        popularAdapter = PopularAdapter(emptyList())
        binding.fragmentHomeRvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvPopular.adapter = popularAdapter
        viewModel.popular.observe(viewLifecycleOwner) { popularList ->
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
            userHandler.removeUserId()
            userHandler.removeUserName()
            userHandler.removeCheckBoxState()
            action = HomeFragmentDirections.actionHomeFragmentToLogInFragment()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, true)
                .setLaunchSingleTop(true)
                .build()
            mNavController.navigate(action, navOptions)
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