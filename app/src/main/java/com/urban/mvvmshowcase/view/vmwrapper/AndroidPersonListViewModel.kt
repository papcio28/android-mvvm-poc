package com.urban.mvvmshowcase.view.vmwrapper

import android.databinding.ObservableBoolean
import com.urban.mvvmshowcase.model.repository.PersonRepository
import com.urban.mvvmshowcase.viewmodel.PersonListNavigator
import com.urban.mvvmshowcase.viewmodel.PersonListViewModel

class AndroidPersonListViewModel(personRepository: PersonRepository,
                                 personListNavigator: PersonListNavigator) :
        PersonListViewModel(personRepository, personListNavigator) {
    val loadingObservable = ObservableBoolean(true)

    override var loading: Boolean
        get() = loadingObservable.get()
        set(value) {
            loadingObservable.set(value)
        }
}