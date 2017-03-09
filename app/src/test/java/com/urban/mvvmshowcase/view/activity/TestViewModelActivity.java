package com.urban.mvvmshowcase.view.activity;

import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.viewmodel.ViewModel;

public class TestViewModelActivity extends AbstractViewModelActivity<ViewModel> {
    private ViewModel testViewModel;

    void setTestViewModel(ViewModel testViewModel) {
        this.testViewModel = testViewModel;
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return testViewModel;
    }
}
