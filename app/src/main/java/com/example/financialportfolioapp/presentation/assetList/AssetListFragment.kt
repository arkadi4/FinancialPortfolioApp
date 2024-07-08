package com.example.financialportfolioapp.presentation.assetList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.financialportfolioapp.R
import com.example.financialportfolioapp.databinding.FragmentAssetListBinding

class AssetListFragment : Fragment() {

    private var _binding: FragmentAssetListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentAssetListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener() {
            findNavController().navigate(R.id.action_asset_list_to_home_fragment)
        }
    }
}