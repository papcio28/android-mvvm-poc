package com.urban.mvvmshowcase.view.activity;

import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.view.vmwrapper.SavingStateViewModel;

public class TestSavingStateViewModelActivity
        extends AbstractSavingStateViewModelActivity<SavingStateViewModel> {
    private SavingStateViewModel viewModel;

    void setTestViewModel(SavingStateViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    protected SavingStateViewModel createViewModel() {
        return viewModel;
    }
}
