package com.example.financialportfolioapp.presentation.partfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.financialportfolioapp.databinding.FragmentPortfolioListBinding

class PortfolioListFragment : Fragment() {

    private var _binding: FragmentPortfolioListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortfolioListBinding.inflate(inflater, container, false)
        return binding.root
    }
}