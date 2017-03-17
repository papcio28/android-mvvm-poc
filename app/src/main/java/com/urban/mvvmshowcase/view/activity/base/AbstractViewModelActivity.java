package com.urban.mvvmshowcase.view.activity.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.urban.mvvmshowcase.viewmodel.ViewModel;

public abstract class AbstractViewModelActivity<T extends ViewModel> extends AppCompatActivity {
    private T viewModel;

    @NonNull
    protected abstract T createViewModel();

    protected T vm() {
        return viewModel;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = (T) getLastCustomNonConfigurationInstance();
        if (viewModel == null) {
            viewModel = createViewModel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onShow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onHide();
    }

    @Override
    public final Object onRetainCustomNonConfigurationInstance() {
        return viewModel;
    }
}
