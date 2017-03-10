package com.urban.mvvmshowcase.viewmodel;

import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

public class PersonCreateViewModel implements ViewModel {
    private final Navigator navigator;
    private final PersonRepository peopleRepository;

    private Person person = new Person();

    public PersonCreateViewModel(@NonNull Navigator navigator,
                                 @NonNull PersonRepository personRepository) {
        this.navigator = navigator;
        this.peopleRepository = personRepository;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void onSavePerson() {
        peopleRepository.add(person);
        navigator.hide();
    }

    @Override
    public void onShow() {
        // no-op
    }

    @Override
    public void onHide() {
        // no-op
    }
}
