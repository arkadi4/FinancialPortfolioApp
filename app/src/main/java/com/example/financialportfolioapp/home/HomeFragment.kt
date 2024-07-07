// HomeFragment.kt
package com.example.financialportfolioapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.databinding.FragmentHomeBinding
import com.example.financialportfolioapp.partfolioList.PartfolioListFragment

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoToPortfolio.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_portfolio_list_fragment)
        }

        binding.btnGoToAssetList.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_asset_list)
        }
        binding.btnGoToSettings.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_settings_fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

}