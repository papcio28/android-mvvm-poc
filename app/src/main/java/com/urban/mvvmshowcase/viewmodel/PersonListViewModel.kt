package com.urban.mvvmshowcase.viewmodel

import android.databinding.ObservableBoolean
import com.urban.mvvmshowcase.model.entity.Person
import com.urban.mvvmshowcase.model.repository.PersonRepository
import io.reactivex.disposables.CompositeDisposable

open class PersonListViewModel(private val personRepository: PersonRepository,
                               var viewAccess: PersonListViewAccess?) : ViewModel {
    val loading = ObservableBoolean(false)
    val disposables = CompositeDisposable()

    val people = mutableListOf<Person>()

    fun loadPeople() {
        disposables.add(personRepository.peopleList()
                .doOnSubscribe { loading.set(true) }
                .doOnNext { loading.set(false) }
                .subscribe {
                    people.clear()
                    people.addAll(it)

                    viewAccess?.notifyDataChanged()
                })
    }

    fun cancelLoading() {
        disposables.clear()
    }
}