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
import com.ali.advancedtask.core.State
import com.ali.advancedtask.core.storge_manager.StorageHandler
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.databinding.FragmentHomeBinding
import com.ali.advancedtask.feature.activities.MainActivity
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
    lateinit var userHandler: UserHandler

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

        val firstName = userHandler.getUserName()?.substringBefore(" ").orEmpty()
        val greetingMessage = getString(R.string.greeting_message, firstName)
        binding.fragmentHomeTvUserName.text = greetingMessage

        categoriesAdapter = CategoryAdapter(emptyList())
        binding.fragmentHomeRvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvCategory.adapter = categoriesAdapter

        trendingAdapter = TrendingAdapter(emptyList())
        binding.fragmentHomeRvTrending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvTrending.adapter = trendingAdapter

        popularAdapter = PopularAdapter(emptyList()){}
        binding.fragmentHomeRvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvPopular.adapter = popularAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.fetchCategoriesPopularTrendingData()
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.state.collect { state ->
                when (state) {
                    is State.Loading -> binding.fragmentHomeProgressBar.visibility = View.VISIBLE
                    is State.Success -> {
                        binding.fragmentHomeProgressBar.visibility = View.GONE
                        if (state.data.baseCategoriesResponseDto.success && state.data.popularSellersResponseDto.success && state.data.trendingSellersResponseDto.success) {
                            categoriesAdapter = CategoryAdapter(state.data.baseCategoriesResponseDto.data!!)
                            binding.fragmentHomeRvCategory.adapter = categoriesAdapter
                            popularAdapter = PopularAdapter(state.data.popularSellersResponseDto.data) {
                                    val action = HomeFragmentDirections.actionHomeFragmentToAdFragment() // Navigate without arguments
                                    mNavController.navigate(action)
                                }
                            binding.fragmentHomeRvPopular.adapter = popularAdapter
                            trendingAdapter =
                                TrendingAdapter(state.data.trendingSellersResponseDto.data)
                            binding.fragmentHomeRvTrending.adapter = trendingAdapter
                        } else {
                            MainActivity.showToast("Error!")
                        }
                    }

                    is State.Error -> {
                        binding.fragmentHomeProgressBar.visibility = View.GONE
                        MainActivity.showToast(state.exception.message ?: "An error occurred")
                    }

                    null -> binding.fragmentHomeProgressBar.visibility = View.GONE
                }

            }
        }

        binding.fragmentHomeIvBackButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }

    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setMessage("Do you want to log out?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            storageHandler.removeAll("YAJHAZ_APP")
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