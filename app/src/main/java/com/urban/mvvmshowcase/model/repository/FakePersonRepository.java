package com.urban.mvvmshowcase.model.repository;

import com.urban.mvvmshowcase.model.entity.Person;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public class FakePersonRepository implements PersonRepository {
    private final List<Person> people = new LinkedList<>();
    private final Subject<List<Person>> peopleSubject = ReplaySubject.createWithSize(1);

    private FakePersonRepository() {
        people.add(new Person("Paweł Urban", 27));
        dispatchRefresh();
    }

    public static FakePersonRepository create() {
        return InstanceHolder.instance;
    }

    private void dispatchRefresh() {
        peopleSubject.onNext(people);
    }

    @Override
    public Observable<List<Person>> peopleList() {
        return peopleSubject;
    }

    @Override
    public void add(Person person) {
        people.add(person);
        dispatchRefresh();
    }

    private static final class InstanceHolder {
        private static final FakePersonRepository instance = new FakePersonRepository();
    }
}
