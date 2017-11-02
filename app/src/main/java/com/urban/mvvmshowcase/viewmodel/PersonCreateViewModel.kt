package com.urban.mvvmshowcase.viewmodel

import android.databinding.BaseObservable
import com.urban.mvvmshowcase.model.entity.Person
import com.urban.mvvmshowcase.model.repository.PersonRepository

class PersonCreateViewModel(private val peopleRepository: PersonRepository,
                            private var viewAccess: PersonCreateViewAccess?) :
        BaseObservable(), ViewModel {
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