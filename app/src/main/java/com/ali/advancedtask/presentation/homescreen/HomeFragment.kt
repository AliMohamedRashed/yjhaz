package com.ali.advancedtask.presentation.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.advancedtask.model.category.CategoryAdapter
import com.ali.advancedtask.model.popular.PopularAdapter
import com.ali.advancedtask.model.trending.TrendingAdapter
import com.ali.advancedtask.model.User
import com.ali.advancedtask.databinding.FragmentHomeBinding
import com.ali.advancedtask.presentation.CategoryViewModel
import com.ali.advancedtask.presentation.PopularViewModel
import com.ali.advancedtask.presentation.TrendingViewModel

class HomeFragment : Fragment() {
    private val categoryViewModel: CategoryViewModel by activityViewModels()
    private lateinit var categoriesAdapter: CategoryAdapter
    private val trendingViewModel: TrendingViewModel by activityViewModels()
    private lateinit var trendingAdapter: TrendingAdapter
    private val popularViewModel: PopularViewModel by activityViewModels()
    private lateinit var popularAdapter: PopularAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        arguments?.let {
            user = HomeFragmentArgs.fromBundle(it).user
        }

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

        binding.fragmentHomeTvUserName.text = "Hello ${user.name.split(" ").first()}"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}