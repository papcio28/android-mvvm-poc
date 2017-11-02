package com.urban.mvvmshowcase.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    public void setPerson(@Nullable Person person) {
        if (person != null) {
            this.person = person;
        }
    }

    public void onSavePerson() {
        peopleRepository.save(person);
        navigator.hide();
    }
}
