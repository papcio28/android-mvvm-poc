package com.urban.mvvmshowcase.viewmodel;

import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

public class PersonCreateViewModel implements ViewModel {
    private String mPersonName;
    private int mPersonAge = 18;

    private final Navigator mNavigator;
    private final PersonRepository mPersonRepository;

    public PersonCreateViewModel(@NonNull Navigator navigator,
                                 @NonNull PersonRepository personRepository) {
        mNavigator = navigator;
        mPersonRepository = personRepository;
    }

    public String getPersonName() {
        return mPersonName;
    }

    public void setPersonName(String personName) {
        mPersonName = personName;
    }

    public int getPersonAge() {
        return mPersonAge;
    }

    public void setPersonAge(int personAge) {
        mPersonAge = personAge;
    }

    public void onSavePerson() {
        mPersonRepository.add(new Person(getPersonName(), getPersonAge()));
        mNavigator.hide();
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onHide() {
    }
}
