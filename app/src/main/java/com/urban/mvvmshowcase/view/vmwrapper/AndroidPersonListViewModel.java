package com.urban.mvvmshowcase.view.vmwrapper;

import android.databinding.ObservableBoolean;

import com.urban.mvvmshowcase.model.repository.PersonRepository;
import com.urban.mvvmshowcase.viewmodel.PersonListViewModel;

public class AndroidPersonListViewModel extends PersonListViewModel {
    @SuppressWarnings("WeakerAccess")
    public final ObservableBoolean loadingObservable = new ObservableBoolean(true);

    public AndroidPersonListViewModel(PersonRepository personRepository, PersonListNavigator navigator) {
        super(personRepository, navigator);
    }

    @Override
    public boolean isLoading() {
        return loadingObservable.get();
    }

    @Override
    public void setLoading(boolean loading) {
        this.loadingObservable.set(loading);
    }
}
