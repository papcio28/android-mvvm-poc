package com.urban.mvvmshowcase.viewmodel;

import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class PersonListViewModel implements ViewModel {
    // Bindables
    private List<Person> mPeople = Collections.emptyList();
    private boolean mLoading = true;

    private PersonRepository mPersonRepository;
    private Disposable mPeopleSubscription;

    private WeakReference<PeopleListObserver> mListObserver = new WeakReference<>(null);

    public PersonListViewModel(PersonRepository personRepository) {
        mPersonRepository = personRepository;
    }

    public void setListObserver(PeopleListObserver listObserver) {
        mListObserver = new WeakReference<>(listObserver);
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(mPeople);
    }

    public boolean isLoading() {
        return mLoading;
    }

    public void setLoading(boolean loading) {
        mLoading = loading;
    }

    @Override
    public void onShow() {
        setLoading(true);
        mPeopleSubscription = mPersonRepository.peopleList()
                .subscribe(this::stopPeopleLoading);
    }

    private void stopPeopleLoading(@NonNull List<Person> people) {
        mPeople = people;
        notifyObserver();
        setLoading(false);
    }

    private void notifyObserver() {
        PeopleListObserver observer = mListObserver.get();
        if (observer != null) {
            observer.onPeopleListChanged();
        }
    }

    @Override
    public void onHide() {
        mPeopleSubscription.dispose();
        mPeopleSubscription = null;
    }

    public interface PeopleListObserver {
        void onPeopleListChanged();
    }
}
