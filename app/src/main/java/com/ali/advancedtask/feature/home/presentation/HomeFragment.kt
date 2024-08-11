package com.ali.advancedtask.feature.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.advancedtask.R
import com.ali.advancedtask.core.storge_manager.StorageHandler
import com.ali.advancedtask.databinding.FragmentHomeBinding
import com.ali.advancedtask.feature.home.domin.viewmodel.HomeViewModel
import com.ali.advancedtask.feature.home.presentation.adapters.CategoryAdapter
import com.ali.advancedtask.feature.home.presentation.adapters.PopularAdapter
import com.ali.advancedtask.feature.home.presentation.adapters.TrendingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    lateinit var storageHandler: StorageHandler
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var popularAdapter: PopularAdapter

    private lateinit var action: NavDirections
    private lateinit var mNavController: NavController

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        mNavController = findNavController()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        arguments?.let {

        }
//        val getUserName = userHandler.getUserName()
//        if (getUserName != null) {
//            binding.fragmentHomeTvUserName.text = "Hello ${getUserName?.split(" ")?.first()}"
//        } else {
//            binding.fragmentHomeTvUserName.text = "Hello ${userName.split(" ").first()}"
//        }
        categoriesAdapter = CategoryAdapter(emptyList())
        binding.fragmentHomeRvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvCategory.adapter = categoriesAdapter

        trendingAdapter = TrendingAdapter(emptyList())
        binding.fragmentHomeRvTrending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvTrending.adapter = trendingAdapter

        popularAdapter = PopularAdapter(emptyList())
        binding.fragmentHomeRvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvPopular.adapter = popularAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.fetchCategories()
        homeViewModel.fetchPopularSellers()
        homeViewModel.fetchTrendingSellers()

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.state.collect { state ->
                categoriesAdapter = CategoryAdapter(state.baseCategories)
                binding.fragmentHomeRvCategory.adapter = categoriesAdapter

                popularAdapter = PopularAdapter(state.popularSellers)
                binding.fragmentHomeRvPopular.adapter = popularAdapter

                trendingAdapter = TrendingAdapter(state.trendingSellers)
                binding.fragmentHomeRvTrending.adapter = trendingAdapter
            }
        }


        binding.fragmentHomeIvBackButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }

    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setMessage("Do you really want to log out?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            storageHandler.removeToken("user_token")
            action = HomeFragmentDirections.actionHomeFragmentToLogInFragment()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, true)
                .setLaunchSingleTop(true)
                .build()
            mNavController.navigate(action, navOptions)
            dialog.dismiss()
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