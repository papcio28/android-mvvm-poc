package com.urban.mvvmshowcase.android;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.model.repository.FakePersonRepository;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

public class MvvmApplication extends Application {
    private final PersonRepository personRepository = FakePersonRepository.create();

    public static MvvmApplication get(@NonNull Context context) {
        return (MvvmApplication) context.getApplicationContext();
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }
}
