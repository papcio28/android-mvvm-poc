package com.urban.mvvmshowcase.person

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

object FakePersonRepository : PersonRepository {
    private val people = mutableListOf<Person>()
    private val peopleSubject = BehaviorSubject.createDefault<List<Person>>(emptyList())

    init {
        people.add(Person(name = "Paweł Urban", age = 27))
        people.add(Person(name = "Jan Kowalski", age = 35))
        dispatchRefresh()
    }

    private fun dispatchRefresh() {
        peopleSubject.onNext(people)
    }

    override fun save(person: Person) {
        val index = people.indexOfFirst { it.id == person.id }
        if (index != -1) {
            people.set(index, person)
        } else {
            people.add(person)
        }
        dispatchRefresh()
    }

    override fun get(personId: UUID): Person? = people.find { it.id == personId }

    override fun peopleList(): Observable<List<Person>> = peopleSubject
}