package com.urban.mvvmshowcase.model.repository

import com.urban.mvvmshowcase.model.entity.Person
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject
import java.util.*

object FakePersonRepository : PersonRepository {
    private val people = mutableListOf<Person>()
    private val peopleSubject = ReplaySubject.createWithSize<List<Person>>(1)

    init {
        people.add(Person("Paweł Urban", 27))
        dispatchRefresh()
    }

    private fun dispatchRefresh() {
        peopleSubject.onNext(people)
    }

    override fun save(person: Person) {
        if (people.contains(person)) {
            people.set(people.indexOf(person), person)
        } else {
            people.add(person)
        }
        dispatchRefresh()
    }

    override fun get(personId: UUID): Person? = people.find { it.id == personId }

    override fun peopleList(): Observable<List<Person>> = peopleSubject
}