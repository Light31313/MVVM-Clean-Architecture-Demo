package com.giangnh44.pagingdemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.giangnh44.pagingdemo.R
import com.giangnh44.pagingdemo.databinding.FragmentDetailImageBinding

class DetailImageFragment : Fragment() {

    private lateinit var binding: FragmentDetailImageBinding
    private val args: DetailImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.image = args.image
    }
}