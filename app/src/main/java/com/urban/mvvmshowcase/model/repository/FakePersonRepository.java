package com.urban.mvvmshowcase.model.repository;

import com.urban.mvvmshowcase.model.entity.Person;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public enum FakePersonRepository implements PersonRepository {
    INSTANCE;

    private final List<Person> mPeople = new LinkedList<>();
    private final Subject<List<Person>> mPeopleSubject = ReplaySubject.createWithSize(1);

    FakePersonRepository() {
        mPeople.add(new Person("Paweł Urban", 27, Collections.emptyList()));
        mPeopleSubject.onNext(mPeople);
    }

    public static FakePersonRepository create() {
        return INSTANCE;
    }

    @Override
    public Observable<List<Person>> peopleList() {
        return mPeopleSubject;
    }
}
