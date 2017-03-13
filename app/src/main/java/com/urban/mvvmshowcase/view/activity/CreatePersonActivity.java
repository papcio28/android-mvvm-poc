package com.urban.mvvmshowcase.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.R;
import com.urban.mvvmshowcase.android.MvvmApplication;
import com.urban.mvvmshowcase.databinding.ActivityCreatePersonBinding;
import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;
import com.urban.mvvmshowcase.view.activity.base.AbstractSavingStateViewModelActivity;
import com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonCreateViewModel;
import com.urban.mvvmshowcase.viewmodel.Navigator;
import com.urban.mvvmshowcase.viewmodel.PersonCreateViewModel;

import java.util.UUID;

public class CreatePersonActivity
        extends AbstractSavingStateViewModelActivity<AndroidPersonCreateViewModel>
        implements Navigator {
    private static final String EXTRA_ID = "id";

    private PersonRepository personRepository;

    @NonNull
    private static Intent openIntent(@NonNull Context context) {
        return new Intent(context, CreatePersonActivity.class);
    }

    public static void start(@NonNull Context context) {
        context.startActivity(openIntent(context));
    }

    public static void startEdit(@NonNull Context context, @NonNull Person person) {
        Intent editIntent = openIntent(context);
        editIntent.putExtra(EXTRA_ID, person.getId());
        context.startActivity(editIntent);
    }

    @NonNull
    @Override
    protected AndroidPersonCreateViewModel createViewModel() {
        return new AndroidPersonCreateViewModel(
                new PersonCreateViewModel(this, personRepository));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        personRepository = MvvmApplication.get(this).getPersonRepository();

        super.onCreate(savedInstanceState);
        ActivityCreatePersonBinding viewBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_person);
        viewBinding.setVm(vm());

        if (wasLaunchedWithPersonId()) {
            vm().setPerson(personRepository.get(getEditPersonId()));
        }
    }

    private UUID getEditPersonId() {
        return (UUID) getIntent().getSerializableExtra(EXTRA_ID);
    }

    private boolean wasLaunchedWithPersonId() {
        return getIntent().hasExtra(EXTRA_ID);
    }

    @Override
    public void hide() {
        finish();
    }
}
