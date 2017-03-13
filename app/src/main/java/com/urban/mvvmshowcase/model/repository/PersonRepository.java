package com.urban.mvvmshowcase.model.repository;

import android.support.annotation.Nullable;

import com.urban.mvvmshowcase.model.entity.Person;

import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;

public interface PersonRepository {
    Observable<List<Person>> peopleList();

    void save(Person person);

    @Nullable
    Person get(UUID personId);
}
