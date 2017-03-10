package com.urban.mvvmshowcase.viewmodel;

import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PersonListViewModel implements ViewModel {
    private final PersonRepository peopleRepository;
    private Disposable peopleSubscription;

    private boolean loading = true;
    private WeakReference<PeopleListObserver> dataObserver = new WeakReference<>(null);

    public PersonListViewModel(PersonRepository personRepository) {
        peopleRepository = personRepository;
    }

    public void setListObserver(PeopleListObserver listObserver) {
        dataObserver = new WeakReference<>(listObserver);
    }

    public boolean isLoading() {
        return loading;
    }

    protected void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void onShow() {
        setLoading(true);
        peopleSubscription = peopleRepository.peopleList()
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> people) throws Exception {
                        stopPeopleLoading(people);
                    }
                });
    }

    private void stopPeopleLoading(@NonNull List<Person> people) {
        notifyObserver(people);
        setLoading(false);
    }

    private void notifyObserver(List<Person> people) {
        PeopleListObserver observer = dataObserver.get();
        if (observer != null) {
            observer.onPeopleListChanged(people);
        }
    }

    @Override
    public void onHide() {
        peopleSubscription.dispose();
        peopleSubscription = null;
    }

    public interface PeopleListObserver {
        void onPeopleListChanged(List<Person> people);
    }
}
