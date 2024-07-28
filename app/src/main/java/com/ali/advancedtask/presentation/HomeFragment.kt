package com.ali.advancedtask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.advancedtask.R
import com.ali.advancedtask.data.CategoryAdapter
import com.ali.advancedtask.data.PopularAdapter
import com.ali.advancedtask.data.PopularItem
import com.ali.advancedtask.data.TrendingAdapter
import com.ali.advancedtask.data.TrendingItems
import com.ali.advancedtask.data.categories
import com.ali.advancedtask.data.popularItems
import com.ali.advancedtask.data.trendingItems
import com.ali.advancedtask.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       //Popular Items RV Code
        binding.fragmentHomeRvPopular.layoutManager =  LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val popularAdapter = PopularAdapter(popularItems)
        binding.fragmentHomeRvPopular.adapter = popularAdapter

        //Trending Items RV Code
        binding.fragmentHomeRvTrending.layoutManager =  LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val trendingAdapter = TrendingAdapter(trendingItems)
        binding.fragmentHomeRvTrending.adapter = trendingAdapter

        //Categories Items RV Code
        binding.fragmentHomeRvCategory.layoutManager =  LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val categoriesAdapter = CategoryAdapter(categories)
        binding.fragmentHomeRvCategory.adapter = categoriesAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment HomeFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(com.ali.advancedtask.ARG_PARAM1, param1)
//                    putString(com.ali.advancedtask.ARG_PARAM2, param2)
//                }
//            }
//    }
}