package com.urban.mvvmshowcase.model.repository;

import com.urban.mvvmshowcase.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;

public interface PersonRepository {
    Observable<List<Person>> peopleList();

    void add(Person person);
}
