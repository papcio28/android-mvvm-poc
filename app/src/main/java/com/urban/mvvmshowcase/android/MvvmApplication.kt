package com.urban.mvvmshowcase.android

import android.app.Application
import android.content.Context
import com.urban.mvvmshowcase.model.repository.FakePersonRepository
import com.urban.mvvmshowcase.model.repository.PersonRepository

class MvvmApplication : Application() {
    val personRepository: PersonRepository = FakePersonRepository

    companion object {
        @JvmStatic
        fun get(context: Context): MvvmApplication = context.applicationContext as MvvmApplication
    }
}