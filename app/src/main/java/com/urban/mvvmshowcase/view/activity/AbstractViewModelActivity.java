package com.urban.mvvmshowcase.view.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.urban.mvvmshowcase.viewmodel.ViewModel;

public abstract class AbstractViewModelActivity<T extends ViewModel> extends AppCompatActivity {
    private T mViewModel;

    protected abstract T createViewModel();

    protected T vm() {
        return mViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureViewModel();
    }

    private void configureViewModel() {
        mViewModel = (T) getLastCustomNonConfigurationInstance();
        if (mViewModel == null) {
            mViewModel = createViewModel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.onShow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModel.onHide();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mViewModel;
    }
}
