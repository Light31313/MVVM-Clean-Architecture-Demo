package com.giangnh44.pagingdemo.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.giangnh44.pagingdemo.databinding.FragmentImageBinding
import com.giangnh44.pagingdemo.ui.adapter.ImageAdapter
import com.giangnh44.pagingdemo.ui.adapter.PagingImageAdapter
import com.giangnh44.pagingdemo.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding

    @Inject
    lateinit var imageAdapter: ImageAdapter

    @Inject
    lateinit var pagingImageAdapter: PagingImageAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("depzai", "onViewCreated: ")
        observeLiveData()
        initView()
        initEvent()
    }

    private fun observeLiveData() {
        viewModel.images.observe(viewLifecycleOwner) {
            imageAdapter.submitList(it)
        }

        viewModel.pagingImages.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                pagingImageAdapter.submitData(pagingData)
            }
        }
    }

    private fun initView() {
        lifecycleScope.launch {
            pagingImageAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.pbLoadImage.isVisible = loadStates.refresh is LoadState.Loading
                binding.txtErrorMessage.isVisible = loadStates.refresh is LoadState.Error
            }
        }
        binding.rvImages.apply {
            // Change to imageAdapter for normal api call without paging
            adapter = pagingImageAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun initEvent() {
        imageAdapter.setOnItemClickListener { image ->
            val action = ImageFragmentDirections.actionImageFragmentToDetailImageFragment(image)
            findNavController().navigate(action)
        }

        pagingImageAdapter.setOnItemClickListener { image ->
            val action = ImageFragmentDirections.actionImageFragmentToDetailImageFragment(image)
            findNavController().navigate(action)
        }

        binding.btnSearch.setOnClickListener {
            // change to viewModel.searImage() for normal api call without paging
            val query = binding.edtSearchImage.text.toString()
            viewModel.searchImagePaging(query)
        }
    }
}