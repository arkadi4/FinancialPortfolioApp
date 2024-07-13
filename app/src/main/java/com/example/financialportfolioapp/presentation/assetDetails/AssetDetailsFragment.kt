package com.example.financialportfolioapp.presentation.assetDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.financialportfolioapp.databinding.FragmentAssetDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetDetailsFragment : Fragment() {

    private var _binding: FragmentAssetDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
