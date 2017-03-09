package com.urban.mvvmshowcase.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.R;
import com.urban.mvvmshowcase.databinding.ActivityCreatePersonBinding;
import com.urban.mvvmshowcase.model.repository.FakePersonRepository;
import com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonCreateViewModel;
import com.urban.mvvmshowcase.viewmodel.Navigator;
import com.urban.mvvmshowcase.viewmodel.PersonCreateViewModel;

public class CreatePersonActivity extends AbstractViewModelActivity<AndroidPersonCreateViewModel>
        implements Navigator {
    public static void start(@NonNull Context context) {
        context.startActivity(new Intent(context, CreatePersonActivity.class));
    }

    @NonNull
    @Override
    protected AndroidPersonCreateViewModel createViewModel() {
        return new AndroidPersonCreateViewModel(
                new PersonCreateViewModel(this, FakePersonRepository.create()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCreatePersonBinding viewBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_person);
        viewBinding.setVm(vm());
    }

    @Override
    public void hide() {
        finish();
    }
}
