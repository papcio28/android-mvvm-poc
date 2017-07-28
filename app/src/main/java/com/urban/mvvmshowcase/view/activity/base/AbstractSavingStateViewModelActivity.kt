package com.urban.mvvmshowcase.view.activity.base

import android.os.Bundle
import com.urban.mvvmshowcase.view.vmwrapper.SavingStateViewModel

abstract class AbstractSavingStateViewModelActivity<out T : SavingStateViewModel>
    : AbstractViewModelActivity<T>() {
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        vm().onSaveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        vm().onRestoreState(savedInstanceState)
    }
}