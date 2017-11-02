package com.urban.mvvmshowcase.android.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class AbstractViewModelActivity<T : Any> : AppCompatActivity() {
    private lateinit var viewModel: T

    protected abstract fun createViewModel(): T

    protected fun vm(): T = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = lastCustomNonConfigurationInstance as? T? ?: createViewModel()
    }

    override fun onRetainCustomNonConfigurationInstance() = viewModel
}