//package com.example.financialportfolioapp.presentation.assetList
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import com.example.financialportfolioapp.databinding.FragmentAssetListBinding
//import com.example.financialportfolioapp.presentation.assetList.rv.AssetListAdapter
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class AssetListFragment : Fragment() {
//    private var _binding: FragmentAssetListBinding? = null
//    private val assetListViewModel: AssetListViewModel by viewModels()
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentAssetListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val adapter = AssetListAdapter()
//        adapter.onClick = onClick@{ asset ->
//            val action = AssetListFragmentDirections.actionAssetListToAssetDetailsFragment(asset.id)
//            findNavController().navigate(action)
//        }
//        adapter.onDeleteClick = { asset ->
//            assetListViewModel.deleteItem(asset)
//        }
//        binding.rv.adapter = adapter
//        assetListViewModel.assets.observe(viewLifecycleOwner) { assets ->
//            adapter.submit(assets)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
