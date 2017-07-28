package com.urban.mvvmshowcase.view.adapter

import com.urban.mvvmshowcase.model.entity.Person

interface ClickListener {
    fun onPersonClick(person: Person)
}