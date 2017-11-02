package com.urban.mvvmshowcase.person.list.adapter

import com.urban.mvvmshowcase.person.Person

interface ClickListener {
    fun onPersonClick(person: Person)
}