package com.example.financialportfolioapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
//import com.example.financialportfolioapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnGoToPortfolio.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToPortfolioListFragment()
//            findNavController().navigate(action)
//        }
//
//        binding.btnGoToAssetList.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToAssetList()
//            findNavController().navigate(action)
//        }
//        binding.btnGoToSettings.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
//            findNavController().navigate(action)
//        }
//        binding.btnGoToSelectAssetType.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToSelectAssetTypeFragment()
//            findNavController().navigate(action)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }
}
