package com.urban.mvvmshowcase.view.activity;

import android.os.Bundle;

import com.urban.mvvmshowcase.view.vmwrapper.SavingStateViewModel;

public abstract class AbstractSavingStateViewModelActivity<T extends SavingStateViewModel>
        extends AbstractViewModelActivity<T> {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        vm().onSaveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        vm().onRestoreState(savedInstanceState);
    }
}
