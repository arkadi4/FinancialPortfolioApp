package com.example.financialportfolioapp.selectAssetType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.financialportfolioapp.databinding.FragmentSelectAssetTypeBinding

class SelectAssetTypeFragment : Fragment() {
    private var _binding: FragmentSelectAssetTypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectAssetTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCreateStock.setOnClickListener {
            val action =
                SelectAssetTypeFragmentDirections.actionSelectAssetTypeFragmentToCreateStockFragment()
            findNavController().navigate(action)
        }

        binding.btnCreateBond.setOnClickListener {
            val action =
                SelectAssetTypeFragmentDirections.actionSelectAssetTypeFragmentToCreateBondFragment()
            findNavController().navigate(action)
        }
        binding.btnCreateCash.setOnClickListener {
            val action =
                SelectAssetTypeFragmentDirections.actionSelectAssetTypeFragmentToCreateCashFragment()
            findNavController().navigate(action)
        }
    }
}
