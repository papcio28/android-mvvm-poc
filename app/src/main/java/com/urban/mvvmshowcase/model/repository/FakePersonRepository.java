package com.urban.mvvmshowcase.model.repository;

import com.urban.mvvmshowcase.model.entity.Person;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public class FakePersonRepository implements PersonRepository {
    private final List<Person> mPeople = new LinkedList<>();
    private final Subject<List<Person>> mPeopleSubject = ReplaySubject.createWithSize(1);

    private FakePersonRepository() {
        mPeople.add(new Person("Paweł Urban", 27, Collections.emptyList()));
        mPeopleSubject.onNext(mPeople);
    }

    public static FakePersonRepository create() {
        return InstanceHolder.sInstance;
    }

    @Override
    public Observable<List<Person>> peopleList() {
        return mPeopleSubject;
    }

    private static final class InstanceHolder {
        private static final FakePersonRepository sInstance = new FakePersonRepository();
    }
}
