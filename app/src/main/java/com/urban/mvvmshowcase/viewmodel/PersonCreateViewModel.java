package com.urban.mvvmshowcase.viewmodel;

import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

public class PersonCreateViewModel implements ViewModel {
    private final Navigator navigator;
    private final PersonRepository peopleRepository;

    // Bindables
    private String mPersonName;
    private int mPersonAge = Person.DEFAULT_AGE;

    public PersonCreateViewModel(@NonNull Navigator navigator,
                                 @NonNull PersonRepository personRepository) {
        this.navigator = navigator;
        peopleRepository = personRepository;
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
        peopleRepository.add(new Person(getPersonName(), getPersonAge()));
        navigator.hide();
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onHide() {
    }
}
