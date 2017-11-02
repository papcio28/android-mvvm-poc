package com.urban.mvvmshowcase.person

import io.reactivex.Observable
import java.util.*

interface PersonRepository {
    fun peopleList(): Observable<List<Person>>

    fun save(person: Person)

    fun get(personId: UUID): Person?
}