package com.urban.mvvmshowcase.model.entity

import java.util.*

data class Person(val id: UUID = UUID.randomUUID(),
                  val name: String = "",
                  val age: Int = Person.DEFAULT_AGE) {
    companion object {
        @JvmField
        val DEFAULT_AGE: Int = 18
    }

    constructor(name: String, age: Int) : this(UUID.randomUUID(), name, age)

    fun withName(name: String) = copy(id, name, age)
    fun withAge(age: Int) = copy(id, name, age)

    override fun toString() = "$name [$age]"
}