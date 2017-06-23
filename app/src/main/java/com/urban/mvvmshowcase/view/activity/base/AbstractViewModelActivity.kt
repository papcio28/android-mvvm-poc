package com.urban.mvvmshowcase.view.activity.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.urban.mvvmshowcase.viewmodel.ViewModel

abstract class AbstractViewModelActivity<out T : ViewModel> : AppCompatActivity() {
    private lateinit var viewModel: T

    protected abstract fun createViewModel(): T

    protected fun vm(): T = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = lastCustomNonConfigurationInstance as? T? ?: createViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onShow()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onHide()
    }

    override fun onRetainCustomNonConfigurationInstance() = viewModel
}