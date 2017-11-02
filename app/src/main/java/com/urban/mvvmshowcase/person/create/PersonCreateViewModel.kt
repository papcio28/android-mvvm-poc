package com.urban.mvvmshowcase.person.create

import android.databinding.BaseObservable
import com.urban.mvvmshowcase.person.Person
import com.urban.mvvmshowcase.person.PersonRepository

class PersonCreateViewModel(private val peopleRepository: PersonRepository,
                            private var viewAccess: PersonCreateViewAccess?) :
        BaseObservable() {
    var person = Person()
    var personName
        get() = person.name
        set(value) {
            person = person.withName(value)
        }
    var personAge: String
        get() = "" + person.age
        set(value) {
            person = person.withAge(Integer.parseInt(value))
        }

    fun savePerson() {
        peopleRepository.save(person)
        viewAccess?.hide()
    }
}