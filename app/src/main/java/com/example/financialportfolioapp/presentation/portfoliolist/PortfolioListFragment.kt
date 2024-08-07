package com.example.financialportfolioapp.presentation.portfoliolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financialportfolioapp.databinding.FragmentPortfolioListBinding
import com.example.financialportfolioapp.presentation.portfoliolist.rv.PortfolioListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioListFragment : Fragment() {

    private var _binding: FragmentPortfolioListBinding? = null
    private val binding get() = _binding!!
    private val portfolioListViewModel: PortfolioListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortfolioListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PortfolioListAdapter()
        binding.portfolioListRv.adapter = adapter
        portfolioListViewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.submit(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
