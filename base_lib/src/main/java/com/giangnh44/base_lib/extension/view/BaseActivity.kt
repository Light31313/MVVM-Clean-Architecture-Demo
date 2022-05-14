package com.giangnh44.base_lib.extension.view

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.giangnh44.base_lib.extension.extension.getBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: throw IllegalStateException("View Binding is not initialized yet")

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getBinding()
        setContentView(binding.root)

        observeLiveData()
        initView()
        initData()
        initEvent()
    }

    open fun observeLiveData() {}

    open fun initView() {}

    open fun initData() {}

    open fun initEvent() {}
}