package com.urban.mvvmshowcase.viewmodel

import com.urban.mvvmshowcase.model.entity.Person
import com.urban.mvvmshowcase.model.repository.PersonRepository
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

interface PersonListNavigator {
    fun openCreatePersonScreen()
    fun openEditPersonScreen(person: Person)
}

interface PeopleListObserver {
    fun onPeopleListChanged(people: List<Person>)
}

open class PersonListViewModel(private val personRepository: PersonRepository,
                               private val navigator: PersonListNavigator)
    : ViewModel, PersonListNavigator by navigator {
    open var loading = true
        protected set
    var listSubscription: Disposable? = null
    private var dataObserver: WeakReference<PeopleListObserver?> =
            WeakReference<PeopleListObserver?>(null)

    fun setListObserver(observer: PeopleListObserver?) {
        dataObserver = WeakReference(observer)
    }

    override fun onShow() {
        loading = true
        listSubscription = personRepository.peopleList().subscribe {
            dataObserver.get()?.onPeopleListChanged(it)
            loading = false
        }
    }

    override fun onHide() {
        listSubscription?.dispose()
        listSubscription = null
    }
}